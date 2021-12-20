package com.atom.jobseeker.post.dao;


import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.pojo.Post;
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
     * @param issueStatus
     */
    void updateBathIssueStatus(@Param("ids") Long[] ids,@Param("issueStatus") Short issueStatus);

    /**
     * 根据id查询状态
     * @param id
     * @return
     */
    Short selectIssueStatus(@Param("id") Long id);

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


    /**
     * 查询公司id对应的所有岗位信息
     * @param id
     * @return
     */
    List<Job> selectListByCompanyId(@Param("id") Long id);

    /**
     * 插入一条数据
     * @param job
     */
    int insert(@Param("job") Job job);

    /**
     * 通过companyId批量删除岗位信息
     * @param companyIds
     */
    void batchDeleteByCompanyId(@Param("companyIds") Long[] companyIds);

    /**
     * 通过id查询岗位和公司的所有数据
     * @param id
     * @return
     */
    Post selectJobAndCompanyById(@Param("id") Long id);

    /**
     * 通过id批量查询岗位和公司的所有数据
     * @param ids
     * @return
     */
    List<Post> batchSelectJobAndCompanyById(@Param("ids") Long[] ids);

    /**
     * 通过公司id查询job表的所有数据
     * @param id
     * @return
     */
    List<Job> selectOldJobList(@Param("ids") Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(@Param("ids") Long[] ids);
}
