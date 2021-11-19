package com.atom.jobseeker.rent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String hStatus;
    private Long cmyId;
    private Long rId;
    private Long cId;
}
