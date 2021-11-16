package com.atom.jobseeker.rent.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sunlei
 */
@Data
public class QueryVo {
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
     * 价格区间划分
     */
    private float startPrice;
    private float endPrice;

    /**
     * 审核状态分为：通过/未通过/待审核
     */
    private String houseStatus;

    public QueryVo() {
    }

    public QueryVo(Map<String, Object> params) {

        this.query = "";
        this.houseStatus = "";
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
        if (keys.contains("houseStatus")) {
            this.houseStatus = (String) params.get("houseStatus");
        }
        if (keys.contains("startPrice")){
            this.startPrice="".equals(params.get("startPrice").toString()) ? 0.0F : Integer.parseInt(params.get("startPrice").toString());
        }
        if (keys.contains("endPrice")){
            this.endPrice= "".equals(params.get("endPrice").toString()) ? 0.0F : Integer.parseInt(params.get("endPrice").toString());
        }
    }

    /**
     * 判断是否带有高级查询
     * @return TRUE带有高级查询FALSE不带高级查询
     */
    public boolean hasQuery(){
        return !"".equals(this.query) || !"".equals(this.houseStatus) ||  cityId != 0 || regionId != 0  || startPrice!=0.0F || endPrice!=0.0F;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "query='" + query + '\'' +
                ", cityId=" + cityId +
                ", regionId=" + regionId +
                ", startPrice=" + startPrice +
                ", endPrice=" + endPrice +
                ", houseStatus='" + houseStatus + '\'' +
                '}';
    }
}
