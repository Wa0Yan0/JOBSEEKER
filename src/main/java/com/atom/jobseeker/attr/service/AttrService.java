package com.atom.jobseeker.attr.service;

import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Major;

import java.util.List;

/**
 * @author wayan
 */
public interface AttrService {

    /**
     * 查询所有城市的信息
     * @return
     */
    List<City> queryCityList();

    /**
     * 查询所有专业的信息
     * @return
     */
    List<Major> queryMajorList();
}
