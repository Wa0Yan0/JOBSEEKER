package com.atom.jobseeker.rent.service;

import com.atom.jobseeker.rent.pojo.Community;

import java.util.List;
import java.util.Map;

/**
 * @author SunLei
 */
public interface CommunityService {
    /**
     * 根据id搜索对应的单条小区记录
     * @param cmyId 小区id
     * @return  带有小区信息的小区对象
     */
    Community queryCommunityById(Long cmyId);


    /**
     * 删除指定id集合的信息
     * @param ids
     * @return
     */
    int delDuplCmy(List<Long> ids);

}
