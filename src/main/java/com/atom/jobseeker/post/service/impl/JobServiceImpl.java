package com.atom.jobseeker.post.service.impl;

import com.atom.jobseeker.attr.dao.AttrDao;
import com.atom.jobseeker.attr.pojo.Region;
import com.atom.jobseeker.common.constant.IssueStatus;
import com.atom.jobseeker.common.utils.IPage;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.pojo.Post;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.post.vo.JobVo;
import com.atom.jobseeker.post.vo.PostVo;
import com.atom.jobseeker.post.vo.QueryVo;
import com.atom.jobseeker.search.es.JobEs;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.atom.jobseeker.common.constant.JobConstant;

import javax.annotation.Resource;
import java.util.*;
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

    @Resource
    private AttrDao attrDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // 将查询参数进行封装
        QueryVo queryVo = new QueryVo(params);
        // 将处理分页的参数进行封装
        IPage iPage = new IPage(params);
        iPage.setTotalCount(queryVo.hasQuery() ? jobDao.selectCountWithQuery(queryVo) : jobDao.selectTotalCount());
        // 将最终返回的数据进行封装
        PageUtils pageUtils = new PageUtils(iPage);
        List<Job> jobs = jobDao.selectListWithQuery(queryVo, iPage.getBegin(), iPage.getPageSize());
        List<JobVo> jobVoList = jobs.stream().map(job -> {
            JobVo jobVo = new JobVo();
            BeanUtils.copyProperties(job, jobVo);
            jobVo.setSalary(job.getSalaryMin() + "-" + job.getSalaryMax());
            Map<String, String> region = attrDao.selectRegionById(job.getRegionId());
            jobVo.setRegion(region.get("cityName") + "-" + region.get("regionName"));
            jobVo.setIssueStatus(IssueStatus.getStatus(job.getIssueStatus()));
            return jobVo;
        }).collect(Collectors.toList());
        pageUtils.setList(jobVoList);
        return pageUtils;
    }

    @Override
    public Job queryJobById(Long id) {
        return jobDao.selectOneById(id);
    }

    @Override
    public void updateBathIssueStatus(Long[] ids, String status) {
        jobDao.updateBathIssueStatus(ids, status);
    }

    @Override
    public List<JobEs> genJobEsList(Long[] ids) {
        //根据当前的id获取job体信息
        List<Job> jobList = jobDao.selectListById(ids);
        return jobList.stream().map(job -> {
            Company company = companyDao.selectOneById(job.getCompanyId());
            JobEs jobEs = new JobEs(job, company);
//            jobEs.setSalary(handleSalary(jobEs.getSalaryText()));
            return jobEs;
        }).collect(Collectors.toList());
    }

    @Override
    public String queryIssueStatus(Long id) {
        return jobDao.selectIssueStatus(id);
    }

    @Override
    public Long[] filterIds(CheckVo checkVo) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Long id : checkVo.getIds()) {
            if (checkVo.getStatus() != null) {
                if ("通过".equals(checkVo.getStatus())) {
                    String issueStatus = jobDao.selectIssueStatus(id);
                    if (!"通过".equals(issueStatus)) {
                        ids.add(id);
                    }
                }
            }
            else {
                String issueStatus = jobDao.selectIssueStatus(id);
                if ("通过".equals(issueStatus)) {
                    ids.add(id);
                }
            }
        }
        return ids.toArray(new Long[0]);
    }

    @Override
    public int save(Job job) {
        job.setIssueDate(new Date());
        return jobDao.insert(job);
    }

    @Override
    public PostVo queryJobAndCompany(Long id) {
        Post post = jobDao.selectJobAndCompanyById(id);
        PostVo postVo = new PostVo();
        BeanUtils.copyProperties(post, postVo);
        postVo.setSalary(post.getSalaryMin() + "-" + post.getSalaryMax());
        postVo.setIssueStatus(IssueStatus.getStatus(post.getIssueStatus()));
        Map<String, String> region = attrDao.selectRegionById(post.getRegionId());
        postVo.setRegion(region.get("cityName") + "-" + region.get("regionName"));
        return postVo;
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
