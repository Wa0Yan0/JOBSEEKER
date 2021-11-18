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
    public List<String> getDuplCmy() {
        List<Community> communities = communityDao.selectAllCmy();
        List<String> idList=new ArrayList<>();
        Map<Long,String> map=new HashMap<>();
        Community community=null;
        String ids;
        Boolean isExist=null;
        while(!communities.isEmpty()){
            ids="";
            isExist=false;
            community=communities.get(0);
            communities.remove(0);
            while(communities.contains(community)){
                isExist=true;
                Community cmy = communities.get(communities.indexOf(community));
                ids+=cmy.getCmyId().toString()+",";
                communities.remove(cmy);
            }
            if(isExist&&!ids.equals("")){
                ids+=community.getCmyId();
                idList.add(ids);
            }
        }
        return idList;
    }

    @Override
    public int delDuplCmy(List<Long> ids) {
        return communityDao.delDupl(ids);
    }


}
