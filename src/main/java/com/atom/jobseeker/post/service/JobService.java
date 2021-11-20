package com.atom.jobseeker.post.service;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.post.vo.JobVo;
import com.atom.jobseeker.post.vo.PostVo;
import com.atom.jobseeker.search.es.JobEs;

import java.util.List;
import java.util.Map;

/**
 * @author wayan
 */
public interface JobService {

    /**
     * 分页查询数据
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过id查找招聘信息
     * @param id
     * @return
     */
    Job queryJobById(Long id);

    /**
     * 修改发布状态
     * @param id
     * @param status
     */
    void updateBathIssueStatus(Long[] id, String status);

    /**
     * 生成jobEs模块的实体
     * @param ids
     * @return
     */
    List<JobEs> genJobEsList(Long[] ids);

    /**
     * 根据id查询岗位状态
     * @param id
     * @return
     */
    String queryIssueStatus(Long id);


    /**
     * 过滤岗位id
     * @param checkVo
     * @return
     */
    Long[] filterIds(CheckVo checkVo);

    /**
     * 保存岗位信息
     * @param job
     */
    int save(Job job);

    /**
     * 查询岗位和公司的所有数据
     * @param id
     * @return
     */
    PostVo queryJobAndCompany(Long id);
}
