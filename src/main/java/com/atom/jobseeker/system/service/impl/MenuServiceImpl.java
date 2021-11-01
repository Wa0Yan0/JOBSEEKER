package com.atom.jobseeker.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.system.dao.MenuDao;
import com.atom.jobseeker.system.pojo.Menu;
import com.atom.jobseeker.system.service.MenuService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Menu> queryMenu() {
        String menus = redisTemplate.opsForValue().get("menus");
        if (StringUtils.isNullOrEmpty(menus)){
            List<Menu> menuList = genMenuList();
            redisTemplate.opsForValue().set("menus", JSON.toJSONString(menuList));
            return menuList;
        }
        return JSON.parseObject(menus, new TypeReference<List<Menu>>(){});
    }

    public List<Menu> genMenuList() {
        List<Menu> menuList = menuDao.selectList();
        return menuList.stream().filter(menu -> menu.getParentId() == 0).peek(menu -> menu.setChildren(genMenuChildren(menu.getMenuId(), menuList))).collect(Collectors.toList());
    }

    public static List<Menu> genMenuChildren(int id, List<Menu> menuList){
        return menuList.stream().filter(menu -> menu.getParentId() == id).collect(Collectors.toList());
    }
}
