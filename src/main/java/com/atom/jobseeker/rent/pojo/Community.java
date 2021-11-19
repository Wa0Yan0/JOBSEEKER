package com.atom.jobseeker.rent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    private Long cmyId;
    private String cmyName;
    private String cmyRegion;
    private String cmyLocation;
    private String cmyStyle;
    private String cmyYear;
    private String cmyCost;
    private String cmyPmc;
    private Long rId;

    @Override
    public boolean equals(Object o) {
        Community community = (Community) o;
        return Objects.equals(cmyName, community.cmyName) && Objects.equals(cmyRegion, community.cmyRegion) && Objects.equals(cmyLocation, community.cmyLocation);
    }

}
