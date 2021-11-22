package com.atom.jobseeker.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atom.jobseeker.search.config.ElasticSearchConfig;
import com.atom.jobseeker.search.constant.EsConstant;
import com.atom.jobseeker.search.es.HouseEs;
import com.atom.jobseeker.search.service.ElasticService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class ElasticServiceImpl implements ElasticService {

    @Resource
    private RestHighLevelClient esClient;

    private <T> String getEsModelId(T esModel){
        String id = "";
        try {
            Field idField = esModel.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            id = idField.get(esModel).toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public <T> boolean upToElastic(List<T> esModelList, String index) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        esModelList.forEach(esModel -> {
            String esModelStr = JSON.toJSONString(esModel);
            IndexRequest indexRequest = new IndexRequest(index).id(getEsModelId(esModel)).source(esModelStr, XContentType.JSON);
            bulkRequest.add(indexRequest);
        });
        BulkResponse bulk = esClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);
        return bulk.hasFailures();
    }

    @Override
    public boolean downFromElastic(Long[] ids, String index) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (Long id : ids) {
            DeleteRequest deleteRequest = new DeleteRequest(index, id.toString());
            bulkRequest.add(deleteRequest);
        }
        BulkResponse bulk = esClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);
        return bulk.hasFailures();
    }
}
