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
    private long totalCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 当前页数
     */
    private int currPage;
    /**
     * 数据列表
     */
    private List<?> list;


    public PageUtils(IPage iPage){
        this.currPage = iPage.getCurrPage();
        this.pageSize = iPage.getPageSize();
        this.totalCount = iPage.getTotalCount();
        this.totalPage = iPage.getTotalPage();
    }
}
