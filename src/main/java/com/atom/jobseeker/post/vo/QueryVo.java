package com.atom.jobseeker.post.vo;

import com.mysql.cj.util.StringUtils;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wayan
 */
@Data
public class QueryVo {
    /**
     * 模糊搜索
     */
    private String query;
    /**
     * 地区id
     */
    private int regionId;
    /**
     * 专业id
     */
    private int majorId;
    /**
     * 发布状态
     */
    private Short issueStatus;
    /**
     * 发布日期
     */
    private String issueDate;


    public QueryVo(Map<String, Object> params) {

        this.query = "";

        this.issueDate = "";

        this.issueStatus = -1;

        // 查询接收信息的所有key
        List<String> keys = params.keySet().stream().map(String::toString).collect(Collectors.toList());

        if (keys.contains("query")) {
            this.query = (String) params.get("query");
        }
        if (keys.contains("regionId") && !StringUtils.isNullOrEmpty(params.get("regionId").toString())) {
            this.regionId = Integer.parseInt(params.get("regionId").toString());
        }
        if (keys.contains("majorId") && !StringUtils.isNullOrEmpty(params.get("majorId").toString())) {
            this.majorId = Integer.parseInt(params.get("majorId").toString());
        }
        if (keys.contains("issueStatus") && !StringUtils.isNullOrEmpty(params.get("issueStatus").toString())) {
            this.issueStatus = Short.parseShort(params.get("issueStatus").toString());
        }
        if (keys.contains("issueDate")) {
            this.issueDate = params.get("issueDate").toString().split("T")[0];
        }
    }

    public boolean hasQuery() {
        return !StringUtils.isNullOrEmpty(query) || issueStatus != -1 || !StringUtils.isNullOrEmpty(this.issueDate) || regionId != 0 || majorId != 0;
    }
}
