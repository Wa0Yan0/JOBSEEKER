package com.atom.jobseeker.search.service;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.search.es.JobEs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wayan
 */
public interface ElasticJobService {

    /**
     * 将数据模型保存到ElasticSearch
     * @param jobEs
     */
    void upToElastic(JobEs jobEs);

    /**
     * 将数据模型从ElasticSearch中删除
     * @param id
     */
    void downFromElastic(Long id);

    /**
     * 从ElasticSearch查询分页数据
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
