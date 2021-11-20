package com.atom.jobseeker.rent.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sunlei
 */
@Data
public class QueryVoCmy {
    /**
     *模糊搜索
     */
    private String query;

    /**
     *根据城市id搜索
     */
    private int cityId;

    /**
     * 根据地区ID搜索
     */
    private int regionId;



    /**
     * 审核状态分为：通过/未通过/待审核
     */
    private String cmyStatus;

    public QueryVoCmy() {
    }

    public QueryVoCmy(Map<String, Object> params) {

        this.query = "";
        this.cmyStatus = "";
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
        if (keys.contains("regionId")) {
            this.regionId = "".equals(params.get("regionId").toString()) ? 0 : Integer.parseInt(params.get("regionId").toString());
        }
    }

    /**
     * 判断是否带有高级查询
     * @return TRUE带有高级查询FALSE不带高级查询
     */
    public boolean hasQuery(){
        return !"".equals(this.query) ||  cityId != 0 || regionId != 0 ;
    }


    @Override
    public String toString() {
        return "QueryVoCmy{" +
                "query='" + query + '\'' +
                ", cityId=" + cityId +
                ", regionId=" + regionId +
                ", cmyStatus='" + cmyStatus + '\'' +
                '}';
    }
}
