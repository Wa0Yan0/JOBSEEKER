package com.atom.jobseeker.rent.service;

import com.atom.jobseeker.rent.pojo.House;
import org.springframework.stereotype.Service;

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
}
