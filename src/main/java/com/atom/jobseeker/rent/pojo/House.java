package com.atom.jobseeker.rent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
    private Long hId;
    private Long rId;
    private String hTitle;
    private Long cmyId;
    private float hMoney;
    private String hManner;
    private String hStyle;
    private String hTowards;
    private String hTag;
    private String hIntro;
    private String hImgUrl;
    private String hFacility;
}
