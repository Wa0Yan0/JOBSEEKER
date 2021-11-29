package com.atom.jobseeker.rent.service;

import com.atom.jobseeker.attr.pojo.Region;
import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.rent.vo.HouseVo;
import com.atom.jobseeker.search.es.HouseEs;

import java.util.List;
import java.util.Map;

/**
 * @author SunLei
 */
public interface HouseService {
    /**
     * 根据单个ID搜索到对应的租房信息
     * @param hId
     * @param regions
     * @return  返回house实例对象
     */
    HouseVo queryHouseById(Long hId,List<Region> regions);

    /**
     * 将搜索分页好的House实例对象封装到分页对象的列表中
     * @param params
     * @param regions
     * @return 返回分页好的实例对象，对象包含House实例对象列表
     */
    PageUtils queryHousesInforWithPage(Map<String, Object> params,List<Region> regions);

    /**
     * 过滤更改状态请求中的id进行上线或下线
     * @param ids
     * @param methodName
     * @return  返回需要更改状态的id集合
     */
    Long[] filterIds(Long[] ids,String methodName);

    /**
     * 生成jobEs模块的实体
     * @param ids
     * @return
     */
    List<HouseEs> genHouseEsList(Long[] ids);

    /**
     * 修改发布状态
     * @param id
     * @param status
     */
    void updateBathIssueStatus(Long[] id, short status);

    /**
     * 将指定cmy_id修改成指定的数值
     * @param oldId
     * @param newId
     * @return
     */
    int updateCmyId(Long oldId,Long newId);

    /**
     * 修改属于指定cmyId小区的城市和地区信息
     * @param cId
     * @param rId
     * @param cmyId
     * @return
     */
    int updateRegion(Long cId,Long rId,Long cmyId);

    /**
     * 删除正式表属于指定cmyId的房屋信息
     * @param ids
     * @return
     */
    int delNewHouseByCmyId(List<Long> ids);

    /**
     * 更新单条对应的房屋信息
     * @param house
     * @return
     */
    int updateOneHouse(House house);

    /**
     * 新增房屋信息
     * @param house
     * @return
     */
    int addNewHouse(House house);

    /**
     * 根据id删除正式表中的房屋信息
     * @param ids
     * @return
     */
    int deleteNewHouseByIds(List<Long> ids);

    /**
     * 获取区域信息
     * @param stringBuffer
     * @param regions
     * @param cityId
     * @param regionId
     * @return
     */
    StringBuffer getRegion(StringBuffer stringBuffer,List<Region> regions,Long cityId,Long regionId);
}
