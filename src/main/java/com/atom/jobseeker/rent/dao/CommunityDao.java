package com.atom.jobseeker.rent.dao;

import com.atom.jobseeker.rent.pojo.Community;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SunLei
 */
@Mapper
public interface CommunityDao {
    /**
     * 根据cmy_id搜索小区单条数据
     * @param cmyId
     * @return 含有小区信息的小区对象
     */
    Community selectOneById(Long cmyId);

    /**
     * 搜索所有小区信息
     * @return
     */
    List<Community> selectAllCmy();

    /**
     * 删除对应id的小区信息
     * @param ids
     * @return
     */
    int delDupl(@Param("ids") List<Long> ids);
}
