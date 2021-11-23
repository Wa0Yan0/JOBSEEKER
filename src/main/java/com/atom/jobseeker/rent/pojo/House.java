package com.atom.jobseeker.rent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
    private Long hId;
    private String hTitle;
    private float hMoney;
    private String hManner;
    private String hStyle;
    private String hTowards;
    private String hTag;
    private String hIntro;
    private String hImgUrl;
    private String hFacility;
    private String hPay;
    private String hRequires;
    private String hDate;
    private Short hStatus;
    private Long cmyId;
    private Long regionId;
    private Long cityId;

    public void sethDate(Date hDate) {
        this.hDate = new SimpleDateFormat("yyyy-MM-dd").format(hDate);
    }
}
