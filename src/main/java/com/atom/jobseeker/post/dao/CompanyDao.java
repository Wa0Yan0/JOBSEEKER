package com.atom.jobseeker.post.dao;


import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.vo.CompanyNameVo;
import com.atom.jobseeker.post.vo.CompanyVo;
import com.atom.jobseeker.post.vo.QueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wayan
 */

@Mapper
public interface CompanyDao {
    /**
     * 通过id查询公司信息
     * @param id
     * @return
     */
    Company selectOneById(@Param("id") Long id);

    /**
     * 查询带有查询条件的总记录数
     * @param queryInfo
     * @return
     */
    long selectCountWithQuery(@Param("queryInfo") QueryVo queryInfo);

    /**
     * 查询总记录数
     * @return
     */
    long selectTotalCount();

    /**
     * 查询带有查询条件的记录
     * @param queryInfo
     * @param begin
     * @param pageSize
     * @return
     */
    List<Company> selectListWithQuery(@Param("queryInfo") QueryVo queryInfo, @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 插入表数据
     * @param company
     * @return
     */
    Long insert(@Param("company") Company company);

    /**
     * 跟新表数据
     * @param company
     */
    void update(@Param("company") Company company);

    /**
     * 查询所有数据
     * @return
     */
    List<Company> selectList();

    /**
     * 批量删除数据
     * @param ids
     */
    void batchDelete(@Param("ids") Long[] ids);

    /**
     * 查询所有名称
     * @param query
     * @return
     */
    List<CompanyNameVo> selectNameList(@Param("query") String query);
}
