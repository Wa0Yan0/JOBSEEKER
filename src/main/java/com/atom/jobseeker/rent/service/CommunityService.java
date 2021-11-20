package com.atom.jobseeker.rent.service;

import com.atom.jobseeker.rent.pojo.Community;
import com.atom.jobseeker.rent.utils.PageUtils;

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

    /**
     * 将搜索分页好的House实例对象封装到分页对象的列表中
     * @param params
     * @return 返回分页好的实例对象，对象包含House实例对象列表
     */
    PageUtils queryCmyListsWithPage(Map<String, Object> params);


    /**
     *更新对应ID的小区信息
     * @param community
     * @return
     */
    int updateCmy(Community community);

}
