package com.atom.jobseeker.post.dao;


import com.atom.jobseeker.post.pojo.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 通过id查找公司名称
     * @param id
     * @return
     */
    String selectNameById(@Param("id") Long id);
}
