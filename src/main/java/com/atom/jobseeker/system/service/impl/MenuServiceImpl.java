package com.atom.jobseeker.system.service.impl;

import com.atom.jobseeker.system.dao.MenuDao;
import com.atom.jobseeker.system.pojo.Menu;
import com.atom.jobseeker.system.service.MenuService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wayan
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    /**
     * Cacheable注解：判断缓存中是否有数据，如果没有就将该方法的返回值放入缓存，如果有，不再调用该方法
     * value： menus为自定义分区名称，数据将会保存到这个分区
     * key: #root.methodName,把方法名称作为key的名称
     * @return
     */
    @Cacheable(value = {"menus"}, key = "#root.methodName", sync = true)
    @Override
    public List<Menu> queryMenu() {
        System.out.println("该方法被调用");
        List<Menu> menuList = menuDao.selectList();
        return menuList.stream().filter(menu -> menu.getParentId() == 0).peek(menu ->
                menu.setChildren(menuList.stream().filter(menuChild -> menuChild.getParentId() == menu.getMenuId()).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    @Override
    public List<Menu> queryParentMenus() {
        return menuDao.selectParentMenus();
    }

    @Override
    public Menu queryOneById(Long menuId) {
        return menuDao.selectOneById(menuId);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public void save(Menu menu) {
        menuDao.save(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public void delete(Long id) {
        menuDao.delete(id);
    }


}
