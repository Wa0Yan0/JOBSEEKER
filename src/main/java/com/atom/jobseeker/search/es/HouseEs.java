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
    private Long hId;
    private String hManner;
    private String hTtile;
    private float hMoney;
    private String hStyle;
    private String cmyName;
    private String cmyRegion;
    private String hImgUrl;

    public HouseEs(House house, Community community) {
        this.hId = house.getHId();
        this.hManner = house.getHManner();
        this.hTtile = house.getHTitle();
        this.hMoney = house.getHMoney();
        this.hStyle = "";
        this.cmyName = community.getCmyName();
        this.cmyRegion = community.getCmyRegion();
        this.hImgUrl = house.getHImgUrl().split(";")[0];
    }
}
