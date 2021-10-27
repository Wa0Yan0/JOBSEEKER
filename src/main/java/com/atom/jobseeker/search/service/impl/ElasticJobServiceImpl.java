package com.atom.jobseeker.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atom.jobseeker.common.utils.IPage;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.search.config.ElasticSearchConfig;
import com.atom.jobseeker.search.constant.EsConstant;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticJobService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wayan
 */
@Slf4j
@Service
public class ElasticJobServiceImpl implements ElasticJobService {

    @Resource
    private RestHighLevelClient esClient;

    @Override
    public void upToElastic(JobEs jobEs) {
        try {
            String jobEsStr = JSON.toJSONString(jobEs);
            IndexRequest indexRequest = new IndexRequest(EsConstant.JOB_INDEX).id(jobEs.getJobId().toString()).source(jobEsStr, XContentType.JSON);
            esClient.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            //TODO 上传失败
            e.printStackTrace();
        }
    }

    @Override
    public void downFromElastic(Long id) {
        try {
            esClient.delete(new DeleteRequest(EsConstant.JOB_INDEX, id.toString()), ElasticSearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            //TODO 下架失败
            e.printStackTrace();
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<JobEs> jobEsList = new ArrayList<>();
        IPage iPage = new IPage(params);
        CountResponse countResponse = null;
        SearchResponse searchResponse = null;
        try {
            CountRequest countRequest = new CountRequest().indices(EsConstant.JOB_INDEX);
            countResponse = esClient.count(countRequest, ElasticSearchConfig.COMMON_OPTIONS);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery()).from(iPage.getBegin()).size(iPage.getPageSize());
            SearchRequest searchRequest = new SearchRequest().indices(EsConstant.JOB_INDEX).source(searchSourceBuilder);
            searchResponse = esClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (countResponse != null && searchResponse != null){
            iPage.setTotalCount(countResponse.getCount());
            jobEsList = Arrays.stream(searchResponse.getHits().getHits()).map(hit -> {
                String source = hit.getSourceAsString();
                return JSON.parseObject(source, JobEs.class);
            }).collect(Collectors.toList());
        }
        PageUtils pageUtils = new PageUtils(iPage);
        pageUtils.setList(jobEsList);
        return pageUtils;
    }
}
