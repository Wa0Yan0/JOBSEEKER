package com.atom.jobseeker.rent.dao;

import com.atom.jobseeker.rent.vo.QueryVo;
import com.atom.jobseeker.rent.pojo.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SunLei
 */
@Mapper
public interface HouseDao {
    /**
     * 根据ID搜索单例租房数据
     * @param hId
     * @return 返回House实例对象
     */
    House selectOneById(Long hId);

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
     int selectTotalCountWithQuery(QueryVo queryVo);

    /**
     * 根据高级查询搜索对应的信息列表
     * @param queryVo
     * @param begin
     * @param pageSize
     * @return  通过高级搜索到的对应的信息列表
     */
    List<House> selectListWithQuery(QueryVo queryVo,int begin,int pageSize);
}
