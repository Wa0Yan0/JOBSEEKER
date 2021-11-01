package com.atom.jobseeker.system.service;

import com.atom.jobseeker.system.pojo.Menu;

import java.util.List;

/**
 * @author wayan
 */

public interface MenuService {

    /**
     * 生成tree型菜单结构
     * @return
     */
    List<Menu> queryMenu();
}
