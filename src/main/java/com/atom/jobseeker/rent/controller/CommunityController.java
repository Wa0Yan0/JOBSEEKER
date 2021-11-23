package com.atom.jobseeker.rent.controller;

import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.rent.pojo.Community;
import com.atom.jobseeker.rent.service.CommunityService;
import com.atom.jobseeker.rent.service.HouseService;
import com.atom.jobseeker.rent.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author SunLei
 */
@RestController
@RequestMapping("/rent/cmy")
public class CommunityController {
    @Autowired
    private CommunityService communityService;
    @Autowired
    private HouseService houseService;

    /**
     * 获取单条小区详情
     * @param cmyId
     * @return
     */
    @RequestMapping("/{id}")
    public R getCmyById(@PathVariable("id") Long cmyId){
        Community community = communityService.queryCommunityById(cmyId);
        return community!=null?R.ok().wrapper("cmyinfor",community):R.error(513,"查询小区信息失败");
    }

    /**
     * 获取小区信息集合
     * @param params
     * @return
     */
    @RequestMapping("/cmylists")
    public R getCmyLists(@RequestParam Map<String, Object> params){
        PageUtils pageUtils = communityService.queryCmyListsWithPage(params);
        return !pageUtils.getList().isEmpty() ? R.ok().wrapper("page", pageUtils):R.error(514,"查询小区信息列表失败");
    }

    /**
     * 更新单条小区信息
     * @param community
     * @return
     */
    @RequestMapping("/update")
    public R updateCmy(@RequestBody Community community){
        int i = communityService.updateCmy(community);
        if (i>0){
            houseService.updateRegion(community.getCityId(), community.getRegionId(),community.getCmyId());
            return R.ok();
        }else {
            return R.error(508,"更新小区信息失败");
        }
    }

    /**
     * 新增小区
     * @param community
     * @return
     */
    @RequestMapping("/add")
    public R addNewCmy(@RequestBody Community community){
        int existsCmy = communityService.isExistsCmy(community);
        if (existsCmy<1){
            communityService.addNewCmy(community);
            return R.ok();
        }else {
            return R.error(509,"该小区已经存在");
        }
    }

    /**
     * 删除小区
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delNewCmy(@RequestBody List<Long> ids){
        houseService.delNewHouseByCmyId(ids);
        return communityService.delNewCmy(ids)>0?R.ok():R.error(515,"小区信息删除失败！");
    }
}
