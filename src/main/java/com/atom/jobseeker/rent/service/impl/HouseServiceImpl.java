package com.atom.jobseeker.rent.service.impl;

import com.atom.jobseeker.rent.dao.HouseDao;
import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.rent.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;

public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseDao houseDao;
    /**
     * 根据单个ID搜索到对应的租房信息
     *
     * @param hId
     * @return 返回house实例对象
     */
    @Override
    public House queryHouseById(Long hId) {
        House house = houseDao.selectOneById(hId);
        return house;
    }
}
