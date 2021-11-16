package com.atom.jobseeker.rent.service;

import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.search.es.HouseEs;
import com.atom.jobseeker.search.es.JobEs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author SunLei
 */
public interface HouseService {
    /**
     * 根据单个ID搜索到对应的租房信息
     * @param hId
     * @return  返回house实例对象
     */
    House queryHouseById(Long hId);

    /**
     * 将搜索分页好的House实例对象封装到分页对象的列表中
     * @param params
     * @return 返回分页好的实例对象，对象包含House实例对象列表
     */
    PageUtils queryHousesInforWithPage(Map<String, Object> params);

    /**
     * 过滤更改状态请求中的id进行上线或下线
     * @param checkVo
     * @return  返回需要更改状态的id集合
     */
    Long[] filterIds(CheckVo checkVo);

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
    void updateBathIssueStatus(Long[] id, String status);
}
