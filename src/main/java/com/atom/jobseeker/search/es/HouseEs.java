package com.atom.jobseeker.search.es;

import com.atom.jobseeker.rent.pojo.Community;
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
public class HouseEs {
    /**
     * 房屋id
     */
    private Long id;
    /**
     * 房屋信息标题
     */
    private String hosTitle;
    /**
     * 房屋租赁方式：整租-单间之类
     */
    private String hosManner;
    /**
     * 租房价格
     */
    private BigDecimal hosMoney;
    /**
     * 房屋类型
     */
    private String hosStyle;
    /**
     * 房屋信息发布时间
     */
    private String hosDate;
    /**
     * 房源图片
     */
    private String hosImgUrl;
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


    public void setHosDate(Date hosDate) {
        this.hosDate = new SimpleDateFormat("yyyy-MM-dd").format(hosDate);
    }
}
