package com.atom.jobseeker.post.service.impl;

import com.atom.jobseeker.common.utils.IPage;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.post.vo.JobVo;
import com.atom.jobseeker.search.es.JobEs;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.atom.jobseeker.common.constant.JobConstant;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wayan
 */
@Service
public class JobServiceImpl implements JobService {

    @Resource
    private JobDao jobDao;

    @Resource
    private CompanyDao companyDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage iPage = new IPage(params);
        iPage.setTotalCount(jobDao.selectTotalCount());
        PageUtils pageUtils = new PageUtils(iPage);
        List<Job> jobs = jobDao.selectListWithLimit(iPage.getBegin(), iPage.getPageSize());
        List<JobVo> jobVos = jobs.stream().map(job -> {
            JobVo jobVo = new JobVo();
            BeanUtils.copyProperties(job, jobVo);
            jobVo.setCompanyName(companyDao.selectNameById(job.getCompanyId()));
            return jobVo;
        }).collect(Collectors.toList());
        pageUtils.setList(jobVos);
        return pageUtils;
    }

    @Override
    public JobVo queryJobById(Long id) {
        Job job = jobDao.selectOneById(id);
        JobVo jobVo = new JobVo();
        BeanUtils.copyProperties(job, jobVo);
        return jobVo;
    }

    @Override
    public void changeIssueStatus(Long id, String status) {
        jobDao.updateIssueStatus(id, status);
    }

    @Override
    public JobEs genJobEs(Long id) {
        Job job = jobDao.selectOneById(id);
        Company company = companyDao.selectOneById(job.getCompanyId());
        JobEs jobEs = new JobEs(job, company);
        jobEs.setSalary(handleSalary(jobEs.getSalaryText()));
        return jobEs;
    }

    @Override
    public String queryIssueStatus(Long id) {
        return jobDao.selectIssueStatus(id);
    }

    @Override
    public Long[] filterIds(CheckVo checkVo) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Long id : checkVo.getIds()) {
            if (!"".equals(checkVo.getStatus())) {
                if ("通过".equals(checkVo.getStatus())) {
                    String issueStatus = jobDao.selectIssueStatus(id);
                    if (!"通过".equals(issueStatus)) {
                        ids.add(id);
                    }
                }
            }else{
                String issueStatus = jobDao.selectIssueStatus(id);
                if ("通过".equals(issueStatus)) {
                    ids.add(id);
                }
            }
        }
        return ids.toArray(new Long[0]);
    }


    /**
     * 计算薪资区间的平均值
     *
     * @param salaryText
     * @return
     */
    private static float handleSalary(String salaryText) {
        float avgSalary = 0.0f;
        if (salaryText.endsWith(JobConstant.unitEnum.UNIT_THOUSAND.getUnit())) {
            String[] salaryList = salaryText.split(JobConstant.unitEnum.UNIT_THOUSAND.getUnit().substring(0, 1))[0].split("-");
            avgSalary = (Float.parseFloat(salaryList[0]) + Float.parseFloat(salaryList[1])) / 2 * 1000;
        } else if (salaryText.endsWith(JobConstant.unitEnum.UNIT_TEN_THOUSAND.getUnit())) {
            String[] salaryList = salaryText.split(JobConstant.unitEnum.UNIT_TEN_THOUSAND.getUnit().substring(0, 1))[0].split("-");
            avgSalary = (Float.parseFloat(salaryList[0]) + Float.parseFloat(salaryList[1])) / 2 * 10000;
        } else {
            String[] salaryList = salaryText.split(JobConstant.unitEnum.UNIT_TEN_THOUSAND.getUnit().substring(0, 1))[0].split("-");
            avgSalary = (Float.parseFloat(salaryList[0]) + Float.parseFloat(salaryList[1])) / 2 * 10000 / 12;
        }
        return avgSalary;
    }
}
