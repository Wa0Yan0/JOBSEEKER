package com.atom.jobseeker.attr.controller;

import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Major;
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

    @RequestMapping("/attrsList")
    public R allAttrList() {
        List<City> cities = attrService.queryCityList();
        List<Major> majors = attrService.queryMajorList();
        return R.ok().wrapper("cityList", cities).wrapper("majorList", majors);
    }
}
