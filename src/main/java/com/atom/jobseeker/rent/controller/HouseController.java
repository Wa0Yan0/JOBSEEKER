package com.atom.jobseeker.rent.controller;

import com.atom.jobseeker.attr.pojo.Region;
import com.atom.jobseeker.attr.service.AttrService;
import com.atom.jobseeker.common.constant.ErrorEnum;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.rent.service.CommunityService;
import com.atom.jobseeker.rent.service.HouseService;
import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.rent.vo.HouseVo;
import com.atom.jobseeker.search.constant.EsConstant;
import com.atom.jobseeker.search.es.HouseEs;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticHouseService;
import com.atom.jobseeker.search.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author SunLei
 */
@RestController
@RequestMapping("/rent/house")
public class HouseController {
    @Autowired
    HouseService houseService;
    @Autowired
    CommunityService communityService;
    @Autowired
    private ElasticService elasticService;
    @Autowired
    private AttrService attrService;

    /**
     * 获取单条租房和对应小区详情
     * @param hId
     * @return
     */
    @RequestMapping("/{id}")
    public R getHouseById(@PathVariable("id") Long hId){
        HouseVo house = houseService.queryHouseById(hId,attrService.queryRegionList());
        return R.ok().wrapper("houseinfor",house).wrapper("cmyinfor",communityService.queryCommunityById(house.getCmyId()));
    }

    /**
     * 获取房屋信息集合
     * @param params
     * @return
     */
    @RequestMapping("/houselists")
    public R getHouseLists(@RequestParam Map<String, Object> params){
        List<Region> regions = attrService.queryRegionList();
        PageUtils pageUtils = houseService.queryHousesInforWithPage(params,regions);
        return R.ok().wrapper("page", pageUtils);
    }


    /**
     * 将需要索引的数据上传到ElasticSearch中，并修改发布状态为通过
     *
     * @param ids
     * @return
     */
    @RequestMapping("/up")
    public R up(@RequestBody Long[] ids) {
        Long[] newIds = houseService.filterIds(ids, "up");
        try {
            if (newIds.length != 0) {
                List<HouseEs> houseEsList = houseService.genHouseEsList(newIds);
                elasticService.upToElastic(houseEsList, EsConstant.House_INDEX);
                houseService.updateBathIssueStatus(ids, (short) 1);
                return R.ok();
            } else {
                return R.error(ErrorEnum.HOUSE_RE_PUSH_ERROR.getCode(), ErrorEnum.HOUSE_RE_PUSH_ERROR.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(ErrorEnum.HOUSE_PUSH_ERROR.getCode(), ErrorEnum.HOUSE_PUSH_ERROR.getMsg());
        }
    }


    @RequestMapping("/update")
    public R updateHouse(@RequestBody House house){
        return houseService.updateOneHouse(house)>0?R.ok():R.error(510,"更新单条房屋信息失败");
    }

    @RequestMapping("/add")
    public R addNewHouse(@RequestBody House house){
        System.out.println(house);
        return houseService.addNewHouse(house)>0?R.ok():R.error(511,"添加单条房屋信息失败");
    }

    @RequestMapping("/delete")
    public R deleteNewHouse(@RequestBody List<Long> ids){
        return houseService.deleteNewHouseByIds(ids)>0?R.ok():R.error(512,"删除房屋信息失败");
    }


}
