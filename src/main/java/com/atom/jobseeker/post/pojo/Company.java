package com.atom.jobseeker.post.pojo;


import lombok.Data;

/**
 * @author wayan
 */

@Data
public class Company {
    private Integer id;
    private String name;
    private String type;
    private String size;
    private String field;
    private String address;
    private String introduce;
    private String img;
    private Integer showStatus;
    private Integer cityId;
    private Integer majorId;
}
