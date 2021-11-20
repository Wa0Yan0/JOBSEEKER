package com.atom.jobseeker.post.vo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PostVo {
    private Long jobId;
    private String jobName;
    private String salary;
    private String welfare;
    private String region;
    private String experience;
    private String education;
    private String peopleCount;
    private String issueDate;
    private String issueStatus;
    private String jobInfo;
    private Long companyId;
    private String companyName;
    private String type;
    private String size;
    private String field;
    private String address;
    private String introduce;
    private String img;

    public void setIssueDate(Date issueDate) {
        this.issueDate = new SimpleDateFormat("yyyy-MM-dd").format(issueDate);
    }
}
