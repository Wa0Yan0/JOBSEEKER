package com.atom.jobseeker.post.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        /**
         *模糊搜索
         */
        this.query = "";
        /**
         * 发布日期
         */
        this.issueDate = "";
        /**
         * 发布状态
         */
        this.issueStatus = "";
        /**
         * 查询接收信息的所有key
         */
        List<String> keys = params.keySet().stream().map(String::toString).collect(Collectors.toList());


        /**
         * 获取到模糊查询的信息
         */
        if (keys.contains("query")){
            this.query = (String) params.get("query");
        }

        if (keys.contains("cityId")) {
            this.cityId = "".equals(params.get("cityId").toString()) ? 0 : Integer.parseInt(params.get("cityId").toString());
        }
        if (keys.contains("majorId")) {
            this.majorId = "".equals(params.get("majorId").toString()) ? 0 : Integer.parseInt(params.get("majorId").toString());
        }
        if (keys.contains("issueStatus")) {
            this.issueStatus = (String) params.get("issueStatus");
        }
        if (keys.contains("issueDate")) {
            this.issueDate = params.get("issueDate").toString().split("T")[0];
        }
    }

    public boolean hasQuery(){
        return !"".equals(this.query) || !"".equals(this.issueStatus) || !"".equals(this.issueDate) || cityId != 0 || majorId != 0;
    }
}
