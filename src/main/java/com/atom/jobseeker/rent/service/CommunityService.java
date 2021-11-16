package com.atom.jobseeker.rent.service;

import com.atom.jobseeker.rent.pojo.Community;

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

}
