package com.atom.jobseeker.system.dao;

import com.atom.jobseeker.system.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wayan
 */
@Mapper
public interface MenuDao {

    /**
     * 查询全部数据
     * @return
     */
    List<Menu> selectList();

    /**
     * 查询所有父菜单的数据
     * @return
     */
    List<Menu> selectParentMenus();

    /**
     * 根据id查询对应菜单数据
     * @param menuId
     * @return
     */
    Menu selectOneById(@Param("menuId") Long menuId);

    /**
     * 保存数据
     * @param menu
     */
    void save(@Param("menu") Menu menu);

    /**
     * 跟新数据
     * @param menu
     */
    void update(@Param("menu") Menu menu);

    /**
     * 删除数据
     * @param id
     */
    void delete(@Param("id") Long id);
}
