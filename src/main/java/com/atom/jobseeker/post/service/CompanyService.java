package com.atom.jobseeker.post.service;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.post.pojo.Company;

import java.util.Map;

/**
 * @author wayan
 */
public interface CompanyService {


    /**
     * 查询带有分页和查询信息的数据
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);


    /**
     * 通过id查询公司信息
     * @param id
     * @return
     */
    Company queryCompanyById(Long id);



}
