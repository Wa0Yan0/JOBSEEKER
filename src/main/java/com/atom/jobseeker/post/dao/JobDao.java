package com.atom.jobseeker.post.dao;


import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.vo.QueryVo;
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
     * @param queryInfo
     * @param begin
     * @param pageSize
     * @return
     */
    List<Job> selectListWithQuery(@Param("queryInfo") QueryVo queryInfo, @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 查询总数
     * @return
     */
    int selectTotalCount();

    /**
     *  批量跟新发布状态
     * @param ids
     * @param status
     */
    void updateBathIssueStatus(@Param("ids") Long[] ids,@Param("status") String status);

    /**
     * 根据id查询状态
     * @param id
     * @return
     */
    String selectIssueStatus(@Param("id") Long id);

    /**
     * 根据id列表批量查询数据
     * @param ids
     * @return
     */
    List<Job> selectListById(@Param("ids") Long[] ids);

    /**
     * 查询带条件的记录总数
     * @param queryInfo
     * @return
     */
    long selectCountWithQuery(@Param("queryInfo") QueryVo queryInfo);
}
