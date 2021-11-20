package com.atom.jobseeker.post.service.impl;

import com.atom.jobseeker.attr.dao.AttrDao;
import com.atom.jobseeker.common.constant.IssueStatus;
import com.atom.jobseeker.common.utils.IPage;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.pojo.Post;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.post.vo.JobVo;
import com.atom.jobseeker.post.vo.PostVo;
import com.atom.jobseeker.post.vo.QueryVo;
import com.atom.jobseeker.search.es.JobEs;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.atom.jobseeker.common.constant.JobConstant;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
        List<Post> postList = jobDao.batchSelectJobAndCompanyById(ids);
        return postList.stream().map(post -> {
            JobEs jobEs = new JobEs();
            BeanUtils.copyProperties(post, jobEs);
            jobEs.setSalaryText(parseSalaryToInt(post.getSalaryMin()) + "-" + parseSalaryToInt(post.getSalaryMax()) + "K");
            Map<String, String> region = attrDao.selectRegionById(post.getRegionId());
            jobEs.setRegionText(region.get("cityName") + "-" + region.get("regionName"));
            jobEs.setSalaryAvg(handleSalaryAvg(post.getSalaryMin(), post.getSalaryMax()));
            return jobEs;
        }).collect(Collectors.toList());
    }

    @Override
    public Short queryIssueStatus(Long id) {
        return jobDao.selectIssueStatus(id);
    }

    @Override
    public Long[] filterIds(Long[] ids, String methodName) {
        return "up".equals(methodName)
                ? Arrays.stream(ids).filter(id -> jobDao.selectIssueStatus(id) == 0).toArray(Long[]::new)
                : Arrays.stream(ids).filter(id -> jobDao.selectIssueStatus(id) == 1).toArray(Long[]::new);
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
     * @param salaryMin
     * @param salaryMax
     * @return
     */
    private static BigDecimal handleSalaryAvg(BigDecimal salaryMin, BigDecimal salaryMax) {
        return salaryMin.add(salaryMax).divide(new BigDecimal("2"));
    }

    private static int parseSalaryToInt(BigDecimal salary){
        return Integer.parseInt(String.valueOf(salary.divide(new BigDecimal("1000.00"))));
    }
}
