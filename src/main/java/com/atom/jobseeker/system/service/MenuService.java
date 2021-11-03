package com.atom.jobseeker.system.service;

import com.atom.jobseeker.system.pojo.Menu;

import java.util.List;

/**
 * @author wayan
 */

public interface MenuService {

    /**
     * 生成树型菜单结构
     * @return
     */
    List<Menu> queryMenu();

    /**
     * 获取所有父菜单
     * @return
     */
    List<Menu> queryParentMenus();

    /**
     * 根据id获取对应的菜单信息
     * @param menuId
     * @return
     */
    Menu queryOneById(Long menuId);

    /**
     * 保存菜单
     * @param menu
     */
    void save(Menu menu);

    /**
     * 跟新菜单
     * @param menu
     */
    void update(Menu menu);
}
