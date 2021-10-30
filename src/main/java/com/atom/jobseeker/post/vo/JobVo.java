package com.atom.jobseeker.post.vo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wayan
 */

@Data
public class JobVo {
    private Long id;
    private String name;
    private String salary;
    private String welfare;
    private String area;
    private String experience;
    private String education;
    private String peopleCount;
    private String issueDate;
    private String issueStatus;
    private String jobInfo;
    private Integer showStatus;
    private String companyName;
    private Long companyId;
}
