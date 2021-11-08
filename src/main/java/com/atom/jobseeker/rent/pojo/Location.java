package com.atom.jobseeker.rent.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private Long rId;
    private String rName;
    private String cId;
    private String url;
}
