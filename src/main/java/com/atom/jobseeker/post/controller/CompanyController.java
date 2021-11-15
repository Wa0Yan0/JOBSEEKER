package com.atom.jobseeker.post.controller;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wayan
 */
@RestController
@RequestMapping("/post/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = companyService.queryPage(params);
        return R.ok().wrapper("page", page);
    }

    @RequestMapping("/{id}")
    public R getCompanyInfo(@PathVariable("id") Long id){
        Company company = companyService.queryCompanyById(id);
        return R.ok().wrapper("company", company);
    }

    @RequestMapping("/save")
    public R save(@RequestBody Company company){
        companyService.save(company);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody Company company){
        companyService.update(company);
        return R.ok();
    }

    @RequestMapping("/generate/table")
    public R generator(){
        companyService.generateTable();
        return R.ok();
    }

}
