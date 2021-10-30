package com.atom.jobseeker.post.controller;

import com.atom.jobseeker.common.constant.ErrorEnum;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.service.CompanyService;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
     * 高级搜索，获取所有数据，带有分页
     *
     * @param params
     * @return
     */
    @RequestMapping("/search/list")
    public R searchList(@RequestParam Map<String, Object> params) {
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
        Job jobInfo = jobService.queryJobById(id);
        Company companyInfo = companyService.queryCompanyById(jobInfo.getCompanyId());
        return R.ok().wrapper("jobInfo", jobInfo).wrapper("companyInfo", companyInfo);
    }

    /**
     * 将岗位信息上传到ElasticSearch中，并修改发布状态
     *
     * @param checkVo
     * @return
     */
    @RequestMapping("/check")
    public R upAndChangeStatus(@RequestBody CheckVo checkVo) {
        Long[] ids = jobService.filterIds(checkVo);
        List<JobEs> jobEsList = jobService.genJobEsList(ids);
        try {
            elasticJobService.upToElastic(jobEsList);
            jobService.updateBathIssueStatus(ids, checkVo.getStatus());
            return R.ok();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(ErrorEnum.JOB_PUSH_ERROR.getCode(), ErrorEnum.JOB_PUSH_ERROR.getMsg());
        }
    }

    /**
     * 从ElasticSearch中下架岗位信息，并修改发布状态
     *
     * @param checkVo
     * @return
     */
    @RequestMapping("/offShelf")
    public R offShelf(@RequestBody CheckVo checkVo) {
        Long[] ids = jobService.filterIds(checkVo);
        try {
            elasticJobService.downFromElastic(ids);
            jobService.updateBathIssueStatus(ids, "待审核");
            return R.ok();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(ErrorEnum.JOB_DOWN_ERROR.getCode(), ErrorEnum.JOB_DOWN_ERROR.getMsg());
        }
    }

}
