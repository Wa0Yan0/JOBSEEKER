package com.atom.jobseeker.attr.service.impl;

import com.atom.jobseeker.attr.dao.AttrDao;
import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Major;
import com.atom.jobseeker.attr.pojo.Region;
import com.atom.jobseeker.attr.service.AttrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wayan
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Resource
    private AttrDao attrDao;

    @Override
    public List<City> queryCityList() {
        return attrDao.selectCityList();
    }

    @Override
    public List<Major> queryMajorList() {
        return attrDao.selectMajorList();
    }

    @Override
    public List<Region> queryRegionList() {
        List<Region> regions = attrDao.selectRegionList();
        return regions.stream().filter(region -> region.getParentId() == 0).peek(region -> {
            List<Region> regionChildren = regions.stream().filter(
                    regionChild -> regionChild.getParentId() != 0 && regionChild.getParentId() == region.getId()
            ).collect(Collectors.toList());
            region.setChildren(regionChildren);
        }).collect(Collectors.toList());
    }
}
