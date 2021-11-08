package com.atom.jobseeker.post.service.impl;

import com.atom.jobseeker.common.utils.IPage;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.service.CompanyService;
import com.atom.jobseeker.post.vo.QueryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author wayan
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryVo queryvo = new QueryVo(params);
        IPage iPage = new IPage(params);
        iPage.setTotalCount(queryvo.hasQuery() ? companyDao.selectCountWithQuery(queryvo) : companyDao.selectTotalCount());
        List<Company> companyList = companyDao.selectListWithQuery(queryvo, iPage.getBegin(), iPage.getPageSize());
        PageUtils pageUtils = new PageUtils(iPage);
        pageUtils.setList(companyList);
        return pageUtils;
    }

    @Override
    public Company queryCompanyById(Long id) {
        return companyDao.selectOneById(id);
    }

}
