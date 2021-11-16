package com.atom.jobseeker.rent.dao;

import com.atom.jobseeker.rent.pojo.Community;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityDao {
    /**
     * 根据cmy_id搜索小区单条数据
     * @param cmyId
     * @return 含有小区信息的小区对象
     */
    Community selectOneById(Long cmyId);

}
