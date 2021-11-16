package com.atom.jobseeker.search.service;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.search.es.HouseEs;
import com.atom.jobseeker.search.es.JobEs;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author SunLei
 */
public interface ElasticHouseService {

    /**
     * 将数据模型保存到ElasticSearch
     * @param houseEs
     * @return boolean
     * @throws IOException
     */
    boolean upToElastic(List<HouseEs> houseEs) throws IOException;

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
