package com.jetsen.web;

import com.alibaba.fastjson.JSON;
import com.jetsen.entity.Customer;
import com.jetsen.entity.ElasticEntity;
import com.jetsen.entity.IdxVo;
import com.jetsen.service.CustomerService;
import com.jetsen.service.ElasticService;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NICKEL
 */
@RestController
@RequestMapping("/elastic")
public class ElasticsearchController {
    @Autowired
    private ElasticService elasticService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createIndex")
    public String createIndex(@RequestBody IdxVo idxVo) {
        if (!elasticService.indexExist(idxVo.getIdxName())) {
            elasticService.createIndex(idxVo.getIdxName(), JSON.toJSONString(idxVo.getIdxDDL()));
        }
        return "ok";
    }

    @GetMapping("/exist/{index}")
    public boolean indexExist(@PathVariable(value = "index") String index) {
        return elasticService.indexExist(index);
    }

    @GetMapping("/del/{index}")
    public String indexDel(@PathVariable(value = "index") String index) {
        elasticService.deleteIndex(index);
        return "ok";
    }

    @GetMapping("/addDoc/{index}")
    public String add(@PathVariable(value = "index") String index) {
        List<Customer> customers = customerService.selectAllCustomer();
        List<ElasticEntity> list = customers.stream().map(customer -> {
            ElasticEntity entity = new ElasticEntity();
            entity.setId(customer.getId());
            entity.setData(JSON.toJSONString(customer));
            return entity;
        }).collect(Collectors.toList());
        elasticService.insertBatch(index, list);
        return "ok";
    }

    @GetMapping("/getDoc/{index}")
    public List<Customer> get(@PathVariable(value = "index") String index) {
        //term查询时，了段类型必须是keyword
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("lastName", "Fang");
        MatchQueryBuilder termQueryBuilder = QueryBuilders.matchQuery("lastName", "Fang");
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("memo", "代码");

       /* RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("publishTime");
        rangeQueryBuilder.gte("2018-01-26T08:00:00Z");
        rangeQueryBuilder.lte("2018-01-26T20:00:00Z");*/

        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(termQueryBuilder);
        boolBuilder.must(matchQueryBuilder);

        SearchSourceBuilder sb = new SearchSourceBuilder();
        sb.from(0);
        sb.size(20);
        sb.query(boolBuilder);
        List<Customer> customers = elasticService.search(index, sb, Customer.class);
        return customers;
    }

    @GetMapping("/delDoc/{index}")
    public String delDoc(@PathVariable(value = "index") String index) {
        String[] ids = {"2", "3", "4", "5"};
        elasticService.deleteBatch(index, Arrays.asList(ids));
        return "ok";
    }
}
