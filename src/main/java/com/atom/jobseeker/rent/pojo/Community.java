package com.atom.jobseeker.rent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    private Long cmyId;
    private String cmyName;
    private String cmyRegion;
    private String cmyLocation;
    private String cmyStyle;
    private String cmyResidents;
    private String cmyProperty;
    private String cmyYear;
    private String cmyGreen;
    private String cmyPark;
    private String cmyCost;
    private String cmyPmc;
}
