package com.atom.jobseeker.post.pojo;

import lombok.Data;
import org.aopalliance.intercept.Interceptor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wayan
 */

@Data
public class Job {
    private Long id;
    private String name;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private String welfare;
    private String experience;
    private String education;
    private String peopleCount;
    private Date issueDate;
    private String issueStatus;
    private String jobInfo;
    private Long companyId;
    private Integer regionId;
    private Integer majorId;
}
