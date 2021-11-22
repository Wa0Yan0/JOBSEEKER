package com.atom.jobseeker.search.service;

import java.io.IOException;
import java.util.List;

public interface ElasticService {

    /**
     * 将数据模型保存到ElasticSearch
     * @param jobEs
     * @return boolean
     * @throws IOException
     */
    <T> boolean upToElastic(List<T> jobEs, String index) throws IOException;

    /**
     * 将数据模型从ElasticSearch中删除
     * @param ids
     */
    boolean downFromElastic(Long[] ids, String index) throws IOException;
}
