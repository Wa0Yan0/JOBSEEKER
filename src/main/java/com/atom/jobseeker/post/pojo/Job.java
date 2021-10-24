package com.atom.jobseeker.post.pojo;

import lombok.Data;
import org.aopalliance.intercept.Interceptor;

import java.util.Date;

/**
 * @author wayan
 */

@Data
public class Job {
    private Long id;
    private String name;
    private String salary;
    private String welfare;
    private String area;
    private String experience;
    private String education;
    private String peopleCount;
    private Date issueDate;
    private String issueStatus;
    private String jobInfo;
    private Integer showStatus;
    private Long companyId;
    private Integer cityId;
    private Integer majorId;
    private String url;
}
