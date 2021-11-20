package com.atom.jobseeker.search.es;


import lombok.Data;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wayan
 */
@Data
public class JobEs {
    private Long jobId;
    private String jobName;
    private String salaryText;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private BigDecimal salaryAvg;
    private String welfare;
    private String regionText;
    private String experience;
    private String education;
    private String peopleCount;
    private String issueDate;
    private Integer regionId;
    private Integer regionParentId;
    private Long companyId;
    private String companyName;
    private String type;
    private String size;
    private String field;
    private String address;
    private String img;

    public void setIssueDate(Date issueDate) {
        this.issueDate = new SimpleDateFormat("yyyy-MM-dd").format(issueDate);
    }
}

