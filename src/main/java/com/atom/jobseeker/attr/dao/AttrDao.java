package com.atom.jobseeker.attr.dao;

import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Location;
import com.atom.jobseeker.attr.pojo.Major;
import com.atom.jobseeker.attr.pojo.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询
     * @param cityId
     * @return
     */
    List<Location> selectLocationList(@Param("cityId") int cityId);

    /**
     * 插入城市信息
     * @param region
     * @return
     */
    int insert(@Param("region") Region region);

    /**
     * 批量插入地区信息
     * @param regions
     */
    void bathInsert(@Param("regions") List<Region> regions);

    /**
     * 查询attr_region表全部数据
     * @return
     */
    List<Region> selectRegionList();

    /**
     * 根据id查询地区信息
     * @param regionId
     * @return
     */
    Map<String, String> selectRegionById(@Param("regionId") Integer regionId);

    /**
     * 查询所有城市
     * @return
     */
    List<Region> selectRegionParentList();
}
