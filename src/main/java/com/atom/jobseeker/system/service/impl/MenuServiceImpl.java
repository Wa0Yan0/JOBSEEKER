package com.atom.jobseeker.system.service.impl;

import com.atom.jobseeker.system.dao.MenuDao;
import com.atom.jobseeker.system.pojo.Menu;
import com.atom.jobseeker.system.service.MenuService;
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

    @Override
    public List<Menu> genMenuList() {
        List<Menu> menuList = menuDao.selectList();
        return menuList.stream().filter(menu -> menu.getParentId() == 0).peek(menu -> menu.setChildren(genMenuChildren(menu.getMenuId(), menuList))).collect(Collectors.toList());
    }

    public static List<Menu> genMenuChildren(int id, List<Menu> menuList){
        return menuList.stream().filter(menu -> menu.getParentId() == id).collect(Collectors.toList());
    }
}
