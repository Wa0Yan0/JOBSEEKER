package com.atom.jobseeker.post.controller;

import com.atom.jobseeker.common.constant.ErrorEnum;
import com.atom.jobseeker.common.constant.IssueStatus;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.service.CompanyService;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.post.vo.PostVo;
import com.atom.jobseeker.search.constant.EsConstant;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticJobService;
import com.atom.jobseeker.search.service.ElasticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    private ElasticService elasticService;

    /**
     * 查询页面显示的分页数据
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
     * 查询指定id的岗位和企业信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public R getJobInfo(@PathVariable("id") Long id) {
        PostVo post = jobService.queryJobAndCompany(id);
        return R.ok().wrapper("post", post);
    }

    /**
     * 将需要索引的数据上传到ElasticSearch中，并修改发布状态为通过
     *
     * @param ids
     * @return
     */
    @RequestMapping("/up")
    public R up(@RequestBody Long[] ids) {
        Long[] newIds = jobService.filterIds(ids, "up");
        try {
            if (newIds.length != 0) {
                List<JobEs> jobEsList = jobService.genJobEsList(newIds);
                elasticService.upToElastic(jobEsList, EsConstant.JOB_INDEX);
                jobService.updateBathIssueStatus(ids, (short) 1);
                return R.ok();
            } else {
                return R.error(ErrorEnum.JOB_RE_PUSH_ERROR.getCode(), ErrorEnum.JOB_RE_PUSH_ERROR.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(ErrorEnum.JOB_PUSH_ERROR.getCode(), ErrorEnum.JOB_PUSH_ERROR.getMsg());
        }
    }

    /**
     * 将不符合要求的数据，修改为未通过状态
     * @param ids
     * @return
     */
    @RequestMapping("/fail")
    public R delete(@RequestBody Long[] ids) {
        jobService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 从ElasticSearch中下架索引数据，并修改发布状态为待审核
     *
     * @param ids
     * @return
     */
    @RequestMapping("/down")
    public R down(@RequestBody Long[] ids) {
        Long[] newIds = jobService.filterIds(ids, "down");
        try {
            if (ids.length != 0) {
                elasticService.downFromElastic(newIds, EsConstant.JOB_INDEX);
                jobService.updateBathIssueStatus(ids, (short) 0);
                return R.ok();
            } else {
                return R.error(ErrorEnum.JOB_RE_DOWN_ERROR.getCode(), ErrorEnum.JOB_RE_DOWN_ERROR.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(ErrorEnum.JOB_DOWN_ERROR.getCode(), ErrorEnum.JOB_DOWN_ERROR.getMsg());
        }
    }

    /**
     * 保存发布的岗位信息
     * @param job
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody Job job) {
        int count = jobService.save(job);
        return count != 0 ? R.ok() : R.error(ErrorEnum.JOB_SAVE_ERROR.getCode(), ErrorEnum.JOB_SAVE_ERROR.getMsg());
    }


}
