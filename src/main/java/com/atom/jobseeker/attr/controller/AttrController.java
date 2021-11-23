package com.atom.jobseeker.attr.controller;

import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Major;
import com.atom.jobseeker.attr.pojo.Region;
import com.atom.jobseeker.attr.service.AttrService;
import com.atom.jobseeker.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wayan
 */

@RestController
@RequestMapping("/attr/")
public class AttrController {

    @Resource
    AttrService attrService;

    @RequestMapping("/cities")
    public R cityList() {
        List<City> cities = attrService.queryCityList();
        return R.ok().wrapper("cityList", cities);
    }

    @RequestMapping("/regions")
    public R getRegionList() {
        List<Region> regionList = attrService.queryRegionList();
        return R.ok().wrapper("regionList", regionList);
    }

    @RequestMapping("/majors")
    public R getMajorList() {
        List<Major> majorList = attrService.queryMajorList();
        return R.ok().wrapper("majorList", majorList);
    }

    @RequestMapping("/region/parents")
    public R getRegionParentList() {
        List<Region> cities = attrService.queryRegionParentList();
        return R.ok().wrapper("cityList", cities);
    }
}
