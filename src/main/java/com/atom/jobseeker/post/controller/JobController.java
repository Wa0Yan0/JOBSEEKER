package com.atom.jobseeker.post.controller;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.service.CompanyService;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.post.vo.CompanyVo;
import com.atom.jobseeker.post.vo.JobVo;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticJobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.ArrayUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @author wayan
 */
@Slf4j
@RestController
@RequestMapping("/post/job")

public class JobController {

    @Resource
    private JobService jobService;

    @Resource
    private CompanyService companyService;

    @Resource
    private ElasticJobService elasticJobService;

    /**
     * 获取所有数据，带有分页
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = jobService.queryPage(params);
        return R.ok().wrapper("page", page);
    }

    /**
     * 通过id查找对应的岗位信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public R getJobInfo(@PathVariable("id") Long id) {
        JobVo jobInfo = jobService.queryJobById(id);
        CompanyVo companyInfo = companyService.queryCompanyById(jobInfo.getCompanyId());
        return R.ok().wrapper("jobInfo", jobInfo).wrapper("companyInfo", companyInfo);
    }

    /**
     * 修改状态并保存到ElasticSearch中
     *
     * @param checkVo
     * @return
     */
    @RequestMapping("/check")
    public R changeStatusAndPush(@RequestBody CheckVo checkVo) {
        Long[] ids = jobService.filterIds(checkVo);
        System.out.println(ids);
//        JobEs jobEs = jobService.genJobEs(ids);
//        elasticJobService.upToElastic(jobEs);
//        jobService.changeIssueStatus(ids, checkVo.getStatus());
        return R.ok();
    }

    /**
     * 下架岗位信息
     *
     * @param checkVo
     * @return
     */
    @RequestMapping("/offShelf")
    public R offShelf(@RequestBody CheckVo checkVo) {
        Long[] ids = jobService.filterIds(checkVo);
        System.out.println(ids);
//        elasticJobService.downFromElastic(ids);
//        jobService.changeIssueStatus(ids, "待审核");
        return R.ok();
    }

}
