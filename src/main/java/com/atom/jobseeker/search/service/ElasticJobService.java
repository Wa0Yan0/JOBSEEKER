package com.atom.jobseeker.search.service;

import com.atom.jobseeker.search.es.JobEs;

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
}
