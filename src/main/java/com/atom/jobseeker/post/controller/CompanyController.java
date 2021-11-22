package com.atom.jobseeker.post.controller;

import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.common.constant.ErrorEnum;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.service.CompanyService;
import com.atom.jobseeker.post.vo.CompanyNameVo;
import com.atom.jobseeker.post.vo.CompanyVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        System.out.println(company);
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

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        companyService.batchDelete(ids);
        return R.ok();
    }

    @RequestMapping("/name/list")
    public R getNameList(@RequestParam String query){
        List<CompanyNameVo> companyNameList = companyService.queryNameList(query);
        return R.ok().wrapper("companyNameList", companyNameList);
    }

    @RequestMapping("/validate")
    public R validateCompanyName(@RequestParam String companyName){
        Long companyId = companyService.queryCompanyId(companyName);
        return companyId == null ? R.ok() : R.error(ErrorEnum.COMPANY_NAME_EXIT.getCode(), ErrorEnum.COMPANY_NAME_EXIT.getMsg());
    }
}
