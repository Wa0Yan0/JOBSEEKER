package com.atom.jobseeker.post.service.impl;

import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.service.CompanyService;
import com.atom.jobseeker.post.vo.CompanyVo;
import org.springframework.beans.BeanUtils;
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
    public CompanyVo queryCompanyById(Long id) {
        Company job = companyDao.selectOneById(id);
        CompanyVo companyVo = new CompanyVo();
        BeanUtils.copyProperties(job, companyVo);
        return companyVo;
    }

}
