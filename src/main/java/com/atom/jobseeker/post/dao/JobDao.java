package com.atom.jobseeker.post.dao;


import com.atom.jobseeker.post.pojo.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wayan
 */

@Mapper
public interface JobDao {

    /**
     * 通过id查询招聘信息
     * @param id
     * @return
     */
    Job selectOneById(@Param("id") Long id);

    /**
     * 分页查询数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Job> selectListWithLimit(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 查询总数
     * @return
     */
    int selectTotalCount();

    /**
     *  跟新发布状态
     * @param id
     * @param status
     */
    void updateIssueStatus(@Param("id") Long id,@Param("status") String status);

    /**
     * 根据id查询状态
     * @param id
     * @return
     */
    String selectIssueStatus(@Param("id") Long id);
}
