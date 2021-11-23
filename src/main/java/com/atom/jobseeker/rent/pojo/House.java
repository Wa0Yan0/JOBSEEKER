package com.atom.jobseeker.rent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
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
    private BigDecimal hosMoney;
    /**
     * 租赁方式
     */
    private String hosManner;
    /**
     * 房屋类型
     */
    private String hosStyle;
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
     * 房屋图片
     */
    private String hosImgUrl;
    /**
     * 房屋设施
     */
    private String hosFacility;
    /**
     * 交付方式
     */
    private String hosPay;
    /**
     * 租房要求
     */
    private String hosRequires;
    /**
     * 发布日期
     */
    private Date hosDate;
    /**
     * 发布状态
     */
    private Short hosStatus;
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


}
