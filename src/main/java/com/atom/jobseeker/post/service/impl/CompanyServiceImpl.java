package com.atom.jobseeker.post.service.impl;

import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author wayan
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Override
    public Company queryCompanyById(Long id) {
        return companyDao.selectOneById(id);
    }

}
