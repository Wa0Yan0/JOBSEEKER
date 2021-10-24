package com.atom.jobseeker.post.vo;

import lombok.Data;

/**
 * @author wayan
 */
@Data
public class CompanyVo {
    private Integer id;
    private String name;
    private String type;
    private String size;
    private String field;
    private String address;
}
