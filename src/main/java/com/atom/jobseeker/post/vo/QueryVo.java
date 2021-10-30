package com.atom.jobseeker.post.vo;

import lombok.Data;
import java.util.Map;

/**
 * @author wayan
 */
@Data
public class QueryVo {
    private String query;
    private int cityId;
    private int majorId;
    private String issueStatus;
    private String issueDate;

    public QueryVo(Map<String, Object> params) {
        this.query = (String) params.get("query");
        this.cityId = "".equals(params.get("cityId").toString())? 0 : Integer.parseInt(params.get("cityId").toString());
        this.majorId = "".equals(params.get("majorId").toString())? 0 : Integer.parseInt(params.get("majorId").toString());
        this.issueStatus = (String) params.get("issueStatus");
        this.issueDate = params.get("issueDate").toString().split("T")[0];
    }
}
