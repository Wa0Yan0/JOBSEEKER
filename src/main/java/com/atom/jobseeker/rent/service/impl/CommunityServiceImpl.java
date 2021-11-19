package com.atom.jobseeker.rent.service.impl;

import com.atom.jobseeker.rent.dao.CommunityDao;
import com.atom.jobseeker.rent.pojo.Community;
import com.atom.jobseeker.rent.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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


}
