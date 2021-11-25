package com.atom.jobseeker.rent.vo;

import com.atom.jobseeker.common.constant.IssueStatus;
import com.atom.jobseeker.rent.pojo.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author SunLei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseVo {
    /**
     * 房屋id
     */
    private Long hosId;
    /**
     * 房屋标题
     */
    private String hosTitle;
    /**
     * 房租价格
     */
    private String hosMoney;
    /**
     * 交付方式
     */
    private String hosPay;
    /**
     * 租赁方式
     */
    private String hosManner;
    /**
     * 住宅类型
     */
    private String hosStyle;
    /**
     * 房屋类型
     */
    private String hosType;
    /**
     * 朝向楼层
     */
    private String hosTowards;
    /**
     * 房屋亮点
     */
    private String hosTag;
    /**
     * 房屋简介
     */
    private String hosIntro;
    /**
     * 房屋检索缩略图
     */
    private String hosSmallImg;
    /**
     * 房屋图片
     */
    private String hosImgUrl;
    /**
     * 所属地区
     */
    private String hosRegion;
    /**
     * 房屋设施
     */
    private String hosFacility;

    /**
     * 房屋面积
     */
    private String hosArea;
    /**
     * 房屋楼层
     */
    private String hosFloor;
    /**
     * 发布日期
     */
    private String hosDate;
    /**
     * 发布状态
     */
    private String hosStatus;
    /**
     * 小区ID
     */
    private Long cmyId;
    /**
     * 地区id
     */
    private Long regionId;
    /**
     * 城市id
     */
    private Long cityId;

    public HouseVo(House house) {
        this.hosId = house.getHosId();
        this.hosTitle = house.getHosTitle();
        this.hosMoney = house.getHosMoney().toString()+"元/月";
        this.hosManner = house.getHosManner();
        this.hosStyle = house.getHosStyle();
        this.hosSmallImg = house.getHosImgUrl().split(";")[0];
    }

    public void setHosRegion(String hosRegion) {
        this.hosRegion = hosRegion;
    }

    public void setHosDate(String hosDate) {
        this.hosDate = hosDate;
    }

    public void setHosStatus(Short hosStatus) {
        this.hosStatus= IssueStatus.getStatus(hosStatus);
    }
}
