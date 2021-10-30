package com.atom.jobseeker.post.service;

import com.atom.jobseeker.post.pojo.Company;

/**
 * @author wayan
 */
public interface CompanyService {
    /**
     * 通过id查询公司信息
     * @param id
     * @return
     */
    Company queryCompanyById(Long id);
}
