package com.atom.jobseeker.rent.dao;

import com.atom.jobseeker.rent.pojo.House;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author SunLei
 */
@Mapper
public interface HouseDao {
    /**
     * 根据ID搜索单例租房数据
     * @param hId
     * @return 返回House实例对象
     */
    House selectOneById(Long hId);
}
