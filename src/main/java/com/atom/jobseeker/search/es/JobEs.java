package com.atom.jobseeker.search.es;

import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String salaryText;
    private Float salary;
    private String welfare;
    private String area;
    private String experience;
    private String education;
    private String peopleCount;
    private String issueDate;
    private Long companyId;
    private String companyName;
    private String size;
    private String field;
    private String type;
    private Integer cityId;
    private Integer majorId;


    public JobEs(Job job, Company company){
        this.jobId = job.getId();
        this.jobName = job.getName();
        this.salaryText = job.getSalary();
        this.welfare = job.getWelfare();
        this.area = job.getArea();
        this.experience = job.getExperience();
        this.education = job.getEducation();
        this.peopleCount = job.getPeopleCount();
        this.issueDate =job.getIssueDate();
        this.companyId = job.getCompanyId();
        this.companyName = company.getName();
        this.size = company.getSize();
        this.field = company.getField();
        this.type = company.getType();
        this.cityId = job.getCityId();
        this.majorId = job.getMajorId();
    }
}
