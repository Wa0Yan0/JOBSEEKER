package com.atom.jobseeker.rent.service.impl;

import com.atom.jobseeker.rent.dao.CommunityDao;
import com.atom.jobseeker.rent.pojo.Community;
import com.atom.jobseeker.rent.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityDao communityDao;

    @Override
    public Community queryCommunityById(Long cmyId) {
        return communityDao.selectOneById(cmyId);
    }

}
