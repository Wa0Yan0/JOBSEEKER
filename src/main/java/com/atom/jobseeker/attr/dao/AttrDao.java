package com.atom.jobseeker.attr.dao;

import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wayan
 */
@Mapper
public interface AttrDao {
    /**
     * 查询所有城市信息
     * @return
     */
    List<City> selectCityList();

    /**
     * 查询所有专业信息
     * @return
     */
    List<Major> selectMajorList();
}
