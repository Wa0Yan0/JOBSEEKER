package com.atom.jobseeker.rent.controller;

import com.atom.jobseeker.common.constant.ErrorEnum;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.rent.service.CommunityService;
import com.atom.jobseeker.rent.service.HouseService;
import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.search.es.JobEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
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
                List<JobEs> jobEsList = jobService.genJobEsList(ids);
                elasticJobService.upToElastic(jobEsList);
                jobService.updateBathIssueStatus(ids, checkVo.getStatus());
                return R.ok();
            }else {
                return R.error(ErrorEnum.JOB_RE_PUSH_ERROR.getCode(), ErrorEnum.JOB_RE_PUSH_ERROR.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(ErrorEnum.JOB_PUSH_ERROR.getCode(), ErrorEnum.JOB_PUSH_ERROR.getMsg());
        }


    }






}
