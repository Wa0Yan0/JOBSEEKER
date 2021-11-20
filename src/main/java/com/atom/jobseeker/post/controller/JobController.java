package com.atom.jobseeker.post.controller;

import com.atom.jobseeker.common.constant.ErrorEnum;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.service.CompanyService;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.post.vo.PostVo;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticJobService;
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
    private CompanyService companyService;

    @Resource
    private ElasticJobService elasticJobService;

    /**
     * 获取带有分页的所有数据
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
        PostVo post = jobService.queryJobAndCompany(id);
        return R.ok().wrapper("post", post);
    }

    /**
     * 将岗位信息上传到ElasticSearch中，并修改发布状态
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
                elasticJobService.upToElastic(jobEsList);
//                jobService.updateBathIssueStatus(ids, );
                return R.ok();
            }else {
                return R.error(ErrorEnum.JOB_RE_PUSH_ERROR.getCode(), ErrorEnum.JOB_RE_PUSH_ERROR.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(ErrorEnum.JOB_PUSH_ERROR.getCode(), ErrorEnum.JOB_PUSH_ERROR.getMsg());
        }
    }

    @RequestMapping("/fail")
    public R changeStatus(@RequestBody Long[] ids){
        System.out.println(Arrays.toString(ids));
//        jobService.updateBathIssueStatus(checkVo.getIds(), checkVo.getStatus());
        return R.ok();
    }

    /**
     * 从ElasticSearch中下架岗位信息，并修改发布状态
     *
     * @param ids
     * @return
     */
    @RequestMapping("/down")
    public R down(@RequestBody Long[] ids) {
        Long[] filterIds = jobService.filterIds(ids, "down");
//        try {
//            if (ids.length != 0) {
//                elasticJobService.downFromElastic(ids);
//                jobService.updateBathIssueStatus(ids, "待审核");
//                return R.ok();
//            }else {
//                return R.error(ErrorEnum.JOB_RE_DOWN_ERROR.getCode(), ErrorEnum.JOB_RE_DOWN_ERROR.getMsg());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return R.error(ErrorEnum.JOB_DOWN_ERROR.getCode(), ErrorEnum.JOB_DOWN_ERROR.getMsg());
//        }
        return R.ok();
    }

    @RequestMapping("/save")
    public R save(@RequestBody Job job){
        int count = jobService.save(job);
        return count !=0 ? R.ok() : R.error(ErrorEnum.JOB_SAVE_ERROR.getCode(), ErrorEnum.JOB_SAVE_ERROR.getMsg());
    }




}
