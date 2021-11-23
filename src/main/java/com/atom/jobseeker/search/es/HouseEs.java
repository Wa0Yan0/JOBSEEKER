package com.atom.jobseeker.search.es;

import com.atom.jobseeker.rent.pojo.Community;
import com.atom.jobseeker.rent.pojo.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SunLei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseEs {
    /**
     * 房屋id
     */
    private Long id;
    /**
     * 房屋信息标题
     */
    private String hTitle;
    /**
     * 房屋租赁方式：整租-单间之类
     */
    private String hManner;
    /**
     * 租房价格
     */
    private float hMoney;
    /**
     * 房屋类型
     */
    private String hStyle;
    /**
     * 房屋信息发布时间
     */
    private String hDate;
    /**
     * 房源图片
     */
    private String hImgUrl;
    /**
     * 小区名称
     */
    private String cmyName;
    /**
     * 小区所属商圈
     */
    private String cmyRegion;
    /**
     * 区ID
     */
    private Integer regionId;
    /**
     * 城市ID
     */
    private Integer cityId;

    public HouseEs(House house, Community community) {
        this.id = house.getHId();
        this.hTitle = house.getHTitle();
        this.hManner = house.getHManner();
        this.hMoney = house.getHMoney();
        this.hStyle = house.getHStyle();
        this.hDate=house.getHDate();
        this.hImgUrl = house.getHImgUrl().split(";")[0];
        this.cmyName = community.getCmyName();
        this.cmyRegion = community.getCmyRegion();
        this.regionId = Math.toIntExact(community.getRegionId());
        this.cityId = Math.toIntExact(community.getCityId());
    }
}
