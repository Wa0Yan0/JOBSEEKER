package com.atom.jobseeker.post.service;

import com.atom.jobseeker.post.vo.CompanyVo;

/**
 * @author wayan
 */
public interface CompanyService {
    /**
     * 通过id查询公司信息
     * @param id
     * @return
     */
    CompanyVo queryCompanyById(Long id);
}
