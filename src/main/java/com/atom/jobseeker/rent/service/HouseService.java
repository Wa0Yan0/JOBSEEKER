package com.atom.jobseeker.rent.service;

import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.rent.pojo.House;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author SunLei
 */
@Service
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
}
