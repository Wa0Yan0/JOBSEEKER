package com.atom.jobseeker.rent.service.impl;

import com.atom.jobseeker.rent.dao.CommunityDao;
import com.atom.jobseeker.rent.pojo.Community;
import com.atom.jobseeker.rent.service.CommunityService;
import com.atom.jobseeker.rent.utils.IPage;
import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.rent.vo.QueryVoCmy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author SunLei
 */
@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityDao communityDao;

    @Override
    public Community queryCommunityById(Long cmyId) {
        return communityDao.selectOneById(cmyId);
    }


    @Override
    public int delDuplCmy(List<Long> ids) {
        return communityDao.delDupl(ids);
    }

    @Override
    public PageUtils queryCmyListsWithPage(Map<String, Object> params) {
        //将高级查询信息封装到高级查询类
        QueryVoCmy queryVoCmy = new QueryVoCmy(params);
        //将分页信息封装到分页类
        IPage iPage = new IPage(params);
        //根据查询到的总记录数封装到分页类的总记录数中
        iPage.setTotalCount(queryVoCmy.hasQuery() ? communityDao.selectTotalCountWithQuery(queryVoCmy) : communityDao.selectTotalCount());
        //将分页类封装到page工具类
        PageUtils pageUtils = new PageUtils(iPage);
        //根据查询条件查询到信息列表
        List<Community> houses = communityDao.selectListWithQuery(queryVoCmy, iPage.getBegin(), iPage.getPageSize());
        //将信息列表封装到page工具类并返回页面
        pageUtils.setList(houses);
        return pageUtils;
    }

    @Override
    public int updateCmy(Community community) {
        System.out.println(community.getCityId()+","+community.getRegionId()+","+community.getCmyId());
        return communityDao.updateCmy(community);
    }

    @Override
    public Long addNewCmy(Community community) {
         communityDao.insertCmy(community);
         communityDao.insertNewCmy(community);
         List<Long> id = Arrays.asList(community.getCmyId());
         communityDao.delDupl(id);
         return community.getCmyId();
    }

    @Override
    public int isExistsCmy(Community community) {
        return communityDao.selectCmyIsExist(community);
    }

    @Override
    public int delNewCmy(List<Long> ids) {
        return  communityDao.delOneNewCmy(ids);
    }


}
