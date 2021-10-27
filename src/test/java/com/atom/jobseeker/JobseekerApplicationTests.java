package com.atom.jobseeker;

import com.alibaba.fastjson.JSON;
import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.search.config.ElasticSearchConfig;
import com.atom.jobseeker.search.constant.EsConstant;
import com.atom.jobseeker.search.es.JobEs;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class JobseekerApplicationTests {

    @Resource
    private JobDao jobDao;

    @Resource
    private JobService jobService;

    @Resource
    private RestHighLevelClient esClient;

    @Test
    void contextLoads() {
        JobEs jobEs = jobService.genJobEs(114351420L);
        System.out.println(jobEs.toString());
    }

    @Test
    void esClientTest() throws IOException {
        CountRequest countRequest = new CountRequest().indices(EsConstant.JOB_INDEX);
        CountResponse countResponse = esClient.count(countRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(countResponse.getCount());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery()).from(0).size(30);
        SearchRequest searchRequest = new SearchRequest().indices(EsConstant.JOB_INDEX).source(searchSourceBuilder);
        SearchResponse searchResponse = esClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
        List<JobEs> jobEsList = Arrays.stream(searchResponse.getHits().getHits()).map(hit -> {
            String source = hit.getSourceAsString();
            return JSON.parseObject(source, JobEs.class);
        }).collect(Collectors.toList());
        System.out.println(jobEsList);

    }

    @Test
    void filterTest() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(114351420L);
        ids.add(114351420L);
        ids.add(114351420L);
        Long[] longs = ids.toArray(new Long[0]);
        for (Long aLong : longs) {
            System.out.println(aLong);
        }
    }

}
