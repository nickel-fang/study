package com.jetsen.service.impl;

import com.alibaba.fastjson.JSON;
import com.jetsen.entity.ElasticEntity;
import com.jetsen.service.ElasticService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author NICKEL
 */
@Service
@Slf4j
public class ElasticServiceImpl implements ElasticService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void createIndex(String idxName, String idxDDL) {
        try {
            if (this.indexExist(idxName)) {
                log.error(" idxName={} 已经存在,idxSql={}", idxName, idxDDL);
                return;
            }
            CreateIndexRequest request = new CreateIndexRequest(idxName);
            request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
            request.mapping(idxDDL, XContentType.JSON);
//            request.settings() 手工指定Setting
            CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            if (!res.isAcknowledged()) {
                log.error("索引{}创建失败", idxName);
                throw new RuntimeException("初始化失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean indexExist(String idxName) {
        GetIndexRequest request = new GetIndexRequest(idxName);
        /*request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        request.indicesOptions(IndicesOptions.lenientExpandOpen());*/
        boolean isExist = false;
        try {
            isExist = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return isExist;
    }

    @Override
    public void insertOrUpdateOne(String idxName, ElasticEntity entity) {
        IndexRequest request = new IndexRequest(idxName);
        log.error("Data : id={},entity={}", entity.getId(), JSON.toJSONString(entity.getData()));
        request.id(String.valueOf(entity.getId()));
        request.source(entity.getData(), XContentType.JSON);
        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("文档{}索引失败", entity.getId());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertBatch(String idxName, List<ElasticEntity> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(idxName).id(String.valueOf(item.getId()))
                .source(item.getData(), XContentType.JSON)));
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("文档{}索引失败", list.stream().map(p -> p.getId()).collect(Collectors.toList()));
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void deleteBatch(String idxName, Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(idxName, item.toString())));
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("文档{}删除失败", idList.stream().collect(Collectors.toList()));
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(idxName);
        request.source(builder);
        try {
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), c));
            }
            return res;
        } catch (Exception e) {
            log.error("索引{}检索失败", idxName);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteIndex(String idxName) {
        try {
            if (!this.indexExist(idxName)) {
                log.error("删除失败： idxName={} 不存在", idxName);
                return;
            }
            restHighLevelClient.indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("索引{}删除失败：{}", idxName, e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByQuery(String idxName, QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(idxName);
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("索引{}删除文档失败：{}" + idxName, e.toString());
            throw new RuntimeException(e);
        }
    }
}
