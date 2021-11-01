package com.atom.jobseeker.system.pojo;


import lombok.Data;

import java.util.List;

/**
 * @author wayan
 */
@Data
public class Menu {
    private int menuId;
    private int parentId;
    private String name;
    private String path;
    private String type;
    private String icon;
    private List<Menu> children;
}
