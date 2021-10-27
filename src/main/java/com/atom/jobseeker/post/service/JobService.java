package com.atom.jobseeker.post.service;

import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.post.vo.JobVo;
import com.atom.jobseeker.search.es.JobEs;

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
    JobVo queryJobById(Long id);

    /**
     * 修改发布状态
     * @param id
     * @param status
     */
    void changeIssueStatus(Long id, String status);

    /**
     * 生成jobEs模块的实体
     * @param id
     * @return
     */
    JobEs genJobEs(Long id);

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
}
