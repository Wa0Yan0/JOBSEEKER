package com.atom.jobseeker.search.service;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.search.es.JobEs;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wayan
 */
public interface ElasticJobService {

    /**
     * 将数据模型保存到ElasticSearch
     * @param jobEs
     * @return boolean
     * @throws IOException
     */
    boolean upToElastic(List<JobEs> jobEs) throws IOException;

    /**
     * 将数据模型从ElasticSearch中删除
     * @param ids
     */
    boolean downFromElastic(Long[] ids) throws IOException;

    /**
     * 从ElasticSearch查询分页数据
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
