package com.jetsen.service;

import com.jetsen.entity.ElasticEntity;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Collection;
import java.util.List;

/**
 * @author NICKEL
 */
public interface ElasticService {
    public void createIndex(String indexName, String idxDDL);
    public boolean indexExist(String idxName);
    public void insertOrUpdateOne(String idxName, ElasticEntity entity);
    public void insertBatch(String idxName, List<ElasticEntity> list);
    public <T> void deleteBatch(String idxName, Collection<T> idList);
    public <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> c);
    public void deleteIndex(String idxName);
    public void deleteByQuery(String idxName, QueryBuilder builder);
}
