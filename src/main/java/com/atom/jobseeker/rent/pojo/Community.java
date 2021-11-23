package com.atom.jobseeker.rent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author SunLei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    /**
     * 小区id
     */
    private Long cmyId;
    /**
     * 小区名称
     */
    private String cmyName;
    /**
     * 商圈区域
     */
    private String cmyRegion;
    /**
     * 小区地址
     */
    private String cmyLocation;
    /**
     * 建筑类别
     */
    private String cmyStyle;
    /**
     * 建筑年代
     */
    private String cmyYear;
    /**
     * 物业费
     */
    private String cmyCost;
    /**
     * 物业公司
     */
    private String cmyPmc;
    /**
     * 地区id
     */
    private Long regionId;
    /**
     * 城市id
     */
    private Long cityId;

    @Override
    public boolean equals(Object o) {
        Community community = (Community) o;
        return Objects.equals(cmyName, community.cmyName) && Objects.equals(cmyRegion, community.cmyRegion) && Objects.equals(cmyLocation, community.cmyLocation);
    }

}
