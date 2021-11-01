package com.atom.jobseeker.attr.service.impl;

import com.atom.jobseeker.attr.dao.AttrDao;
import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Major;
import com.atom.jobseeker.attr.service.AttrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
