package com.atom.jobseeker.search.es;

import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wayan
 */
@Data
@AllArgsConstructor
public class JobEs {
    private Long jobId;
    private String jobName;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private BigDecimal salaryAvg;
    private String welfare;
    private String experience;
    private String education;
    private String peopleCount;
    private Date issueDate;
    private Long companyId;
    private String companyName;
    private String size;
    private String field;
    private String type;
    private Integer RegionId;
    private Integer majorId;


    public JobEs(Job job, Company company){
        this.jobId = job.getId();
        this.jobName = job.getName();
        this.welfare = job.getWelfare();
        this.experience = job.getExperience();
        this.education = job.getEducation();
        this.peopleCount = job.getPeopleCount();
        this.issueDate =job.getIssueDate();
        this.companyId = job.getCompanyId();
        this.companyName = company.getName();
        this.size = company.getSize();
        this.field = company.getField();
        this.type = company.getType();
        this.majorId = job.getMajorId();
    }
}
