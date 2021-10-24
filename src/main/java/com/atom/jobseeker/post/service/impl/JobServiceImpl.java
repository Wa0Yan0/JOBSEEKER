package com.atom.jobseeker.post.service.impl;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.post.vo.JobVo;
import com.atom.jobseeker.search.es.JobEs;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
        PageUtils pageUtils = new PageUtils(params, jobDao.selectTotalCount());
        int begin = (pageUtils.getCurrPage() - 1) * pageUtils.getPageSize();
        List<Job> jobs = jobDao.selectListWithLimit(begin, pageUtils.getPageSize());
        List<JobVo> jobVos = jobs.stream().map(job -> {
            JobVo jobVo = new JobVo();
            BeanUtils.copyProperties(job, jobVo);
            jobVo.setCompanyName(companyDao.selectNameById(job.getCompanyId()));
            jobVo.setIssueDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(job.getIssueDate()));
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
        jobVo.setIssueDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(job.getIssueDate()));
        return jobVo;
    }

    @Override
    public void changeIssueStatus(Long id, String status) {
        jobDao.updateIssueStatus(id, status);
    }

    @Override
    public JobEs genJobEs(Long id) {
        //TODO 生成jobEs实体
        return null;
    }
}
