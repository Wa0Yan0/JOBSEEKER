package com.atom.jobseeker.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author wayan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageUtils {
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int currPage;
    /**
     * 数据列表
     */
    private List<?> list;

    public PageUtils(Map<String, Object> params, int totalCount){
        this.currPage = Integer.parseInt(params.get("pageNum").toString());
        this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        this.totalCount = totalCount;
        this.totalPage = totalCount % this.pageSize == 0 ? totalCount/this.pageSize : totalCount/this.pageSize + 1;
    }
}
