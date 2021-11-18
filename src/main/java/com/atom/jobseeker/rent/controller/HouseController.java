package com.atom.jobseeker.rent.controller;

import com.atom.jobseeker.common.constant.ErrorEnum;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.rent.service.CommunityService;
import com.atom.jobseeker.rent.service.HouseService;
import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.search.es.HouseEs;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticHouseService;
import com.atom.jobseeker.search.service.ElasticJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private ElasticHouseService elasticHouseService;

    /**
     * 获取单条租房和对应小区详情
     * @param hId
     * @return
     */
    @RequestMapping("/{id}")
    public R getHouseById(@PathVariable("id") Long hId){
        House house = houseService.queryHouseById(hId);
        return R.ok().wrapper("houseinfor",house).wrapper("cmyinfor",communityService.queryCommunityById(house.getCmyId()));
    }

    /**
     * 获取房屋信息集合
     * @param params
     * @return
     */
    @RequestMapping("/houselists")
    public R getHouseLists(@RequestParam Map<String, Object> params){
        PageUtils pageUtils = houseService.queryHousesInforWithPage(params);
        return R.ok().wrapper("page", pageUtils);
    }

    /**
     * 将传值过来的id列表的状态设为通过并发布到elasticsearch
     * @param checkVo
     * @return
     */
    @RequestMapping("/up")
    public R upAndChangeStatus(@RequestBody CheckVo checkVo) {
        //过滤需要的id返回id集合
        Long[] ids = houseService.filterIds(checkVo);
        try {
            if (ids.length != 0) {
                List<HouseEs> HouseEsList = houseService.genHouseEsList(ids);
                elasticHouseService.upToElastic(HouseEsList);
                houseService.updateBathIssueStatus(ids, checkVo.getStatus());
                return R.ok();
            }else {
                return R.error(ErrorEnum.HOUSE_RE_PUSH_ERROR.getCode(), ErrorEnum.HOUSE_RE_PUSH_ERROR.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(ErrorEnum.HOUSE_PUSH_ERROR.getCode(), ErrorEnum.HOUSE_PUSH_ERROR.getMsg());
        }
    }

    /**
     * 将小区重复的信息进行删除并修改对应的租房表
     * @return
     */
    @RequestMapping("/duplrem")
    public R duplicateRemoval(){
        List<String> duplCmy = communityService.getDuplCmy();
        duplCmy.forEach(ids->{
            String[] id = ids.split(",");
            List<Long> newId = Arrays.stream(id).map(Long::parseLong).collect(Collectors.toList());
            for (int i = 0; i < newId.size()-1; i++) {
                houseService.updateCmyId(newId.get(i),newId.get(newId.size()-1));
            }
            newId.remove(newId.size()-1);
            communityService.delDuplCmy(newId);
        });
        return R.ok();
    }
}
