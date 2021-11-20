package com.atom.jobseeker.rent.dao;

import com.atom.jobseeker.rent.pojo.Community;
import com.atom.jobseeker.rent.vo.QueryVoCmy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SunLei
 */
@Mapper
public interface CommunityDao {
    /**
     * 根据cmy_id搜索小区单条数据
     * @param cmyId
     * @return 含有小区信息的小区对象
     */
    Community selectOneById(Long cmyId);

    /**
     * 搜索所有小区信息
     * @return
     */
    List<Community> selectAllCmy();

    /**
     * 删除对应id的小区信息
     * @param ids
     * @return
     */
    int delDupl(@Param("ids") List<Long> ids);

    /**
     * 将临时表community的数据推送到正式表rent_community中
     * @return
     */
    int postCmyNewData();

    /**
     * 清空community表
     * @return
     */
    int delCmy();

    /**
     * 搜索总记录数
     * @return 记录数总数
     */
    int selectTotalCount();

    /**
     * 根据高级查询搜索总记录数
     * @param queryVo
     * @return  高级查询后的总记录数
     */
    int selectTotalCountWithQuery(@Param("queryVo") QueryVoCmy queryVo);

    /**
     * 根据高级查询搜索对应的信息列表
     * @param queryVo
     * @param begin
     * @param pageSize
     * @return  通过高级搜索到的对应的信息列表
     */
    List<Community> selectListWithQuery(@Param("queryVo") QueryVoCmy queryVo, @Param("begin") int begin, @Param("pagesize") int pageSize);

    /**
     * 修改对应的Community信息
     * @param community
     * @return
     */
    int updateCmy(@Param("cmy") Community community);

}
