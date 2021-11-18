package com.atom.jobseeker.attr.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author wayan
 */
@Data
public class Region {
    private int id;
    private String name;
    private int parentId;
    private List<Region> children;

    public Region(){}

    public Region(String name, int parentId){
        this.name = name;
        this.parentId = parentId;
    }
}
