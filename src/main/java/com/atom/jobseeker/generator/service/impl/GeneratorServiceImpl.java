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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author wayan
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Resource
    private CompanyDao companyDao;

    @Resource
    private JobDao jobDao;

    @Resource
    private AttrDao attrDao;

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

}
