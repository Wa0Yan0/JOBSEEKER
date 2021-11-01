package com.atom.jobseeker.common.utils;

import com.atom.jobseeker.post.vo.QueryVo;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wayan
 */
@Data
public class IPage {
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
     * 开始获取的位置
     */
    private int begin;
    /**
     * 查询参数
     */
    private String query;

    public IPage(Map<String, Object> params) {
        this.currPage = 1;
        this.pageSize = 10;
        List<String> keys = params.keySet().stream().map(String::toString).collect(Collectors.toList());
        if (keys.contains("pageNum")) {
            this.currPage = Integer.parseInt(params.get("pageNum").toString());
        }
        if (keys.contains("pageSize")) {
            this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        }
        this.begin = (this.currPage - 1) * this.pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.totalPage = totalCount % this.pageSize == 0 ? totalCount / this.pageSize : totalCount / this.pageSize + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }


    public int getBegin() {
        return begin;
    }
}
