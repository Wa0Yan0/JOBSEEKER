package com.atom.jobseeker.post.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Post {
    private Long jobId;
    private String jobName;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private String welfare;
    private String experience;
    private String education;
    private String peopleCount;
    private Date issueDate;
    private Short issueStatus;
    private String jobInfo;
    private Integer regionId;
    private Integer regionParentId;
    private Integer majorId;
    private Long companyId;
    private String companyName;
    private String type;
    private String size;
    private String field;
    private String address;
    private String introduce;
    private String img;
}
