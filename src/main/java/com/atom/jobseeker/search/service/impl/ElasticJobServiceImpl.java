package com.atom.jobseeker.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atom.jobseeker.search.config.ElasticSearchConfig;
import com.atom.jobseeker.search.constant.EsConstant;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticJobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;

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
}
