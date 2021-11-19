package com.atom.jobseeker.generator.service.impl;


import com.atom.jobseeker.attr.dao.AttrDao;
import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Location;
import com.atom.jobseeker.attr.pojo.Region;
import com.atom.jobseeker.generator.service.GeneratorService;
import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.rent.dao.CommunityDao;
import com.atom.jobseeker.rent.dao.HouseDao;
import com.atom.jobseeker.rent.pojo.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author wayan,sunlei
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Resource
    private CompanyDao companyDao;

    @Resource
    private JobDao jobDao;

    @Resource
    private AttrDao attrDao;

    @Resource
    private CommunityDao communityDao;

    @Resource
    private HouseDao houseDao;


    @Override
    public void genPost() {
        List<Company> companyList = companyDao.selectList();
        companyList.forEach(company -> {
            Long companyId = companyDao.insert(company);
            List<Job> jobList = jobDao.selectListByCompanyId(company.getId());
            if (jobList != null) {
                jobList.forEach(job -> {
                    job.setCompanyId(companyId);
                    jobDao.insert(job);
                });
            }
        });
    }

    @Override
    public void genAttr() {
        List<City> cities = attrDao.selectCityList();
        cities.forEach(city -> {
            Region region = new Region(city.getName(), 0);
            int regionId = attrDao.insert(region);
            if (regionId != 0) {
                List<Location> locations = attrDao.selectLocationList(city.getId());
                if (locations != null) {
                    List<Region> regionChild = locations.stream().map(location ->
                            new Region(location.getRName().endsWith("区") ? location.getRName() : location.getRName() + "区", region.getId())
                    ).collect(Collectors.toList());
                    attrDao.bathInsert(regionChild);
                }
            }

        });
    }


    @Override
    public int dupCmyHandle() {
        int isSuccess=1;
        try{
            List<Community> communities = communityDao.selectAllCmy();
            List<String> idList=new ArrayList<>();
            Map<Long,String> map=new HashMap<>(6);
            Community community=null;
            String idstr;
            Boolean isExist=null;
            while(!communities.isEmpty()){
                idstr="";
                isExist=false;
                community=communities.get(0);
                communities.remove(0);
                while(communities.contains(community)){
                    isExist=true;
                    Community cmy = communities.get(communities.indexOf(community));
                    idstr+=cmy.getCmyId().toString()+",";
                    communities.remove(cmy);
                }
                if(isExist&&!idstr.equals("")){
                    idstr+=community.getCmyId();
                    idList.add(idstr);
                }
            }
            if(!idList.isEmpty()){
                idList.forEach(ids->{
                    String[] id = ids.split(",");
                    List<Long> newId = Arrays.stream(id).map(Long::parseLong).collect(Collectors.toList());
                    for (int i = 0; i < newId.size()-1; i++) {
                        houseDao.updateCmyId(newId.get(i),newId.get(newId.size()-1));
                    }
                    newId.remove(newId.size()-1);
                    communityDao.delDupl(newId);
                });
            }
        }catch (Exception e){
            isSuccess=0;
        }
        return isSuccess;
    }

    @Override
    public int postHouseNewData() {
        return houseDao.postHouseNewData();
    }

    @Override
    public int postCmyNewData() {
        return communityDao.postCmyNewData();
    }

    @Override
    public int delHouse() {
        return houseDao.delHouse();
    }

    @Override
    public int delCmy() {
        return communityDao.delCmy();
    }


}
