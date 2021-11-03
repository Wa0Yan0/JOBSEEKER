package com.atom.jobseeker.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.system.dao.MenuDao;
import com.atom.jobseeker.system.pojo.Menu;
import com.atom.jobseeker.system.service.MenuService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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

    /**
     * 思路：查询redis的缓存数据，如果查询结果是null，从数据库查询数据，否则直接返回缓存数据
     */

//    @Override
//    public List<Menu> queryMenu() {
//        String menusFromRedis = redisTemplate.opsForValue().get("menus");
//        if (StringUtils.isNullOrEmpty(menusFromRedis)){
//            return genMenuList();
//        }
//        return JSON.parseObject(menusFromRedis, new TypeReference<List<Menu>>(){});
//    }

    /**
     * Cacheable注解：判断缓存中是否有数据，如果没有就将该方法的返回值放入缓存，如果有，不再调用该方法
     * value： menus为自定义分区名称，数据将会保存到这个分区
     * key: #root.methodName,把方法名称作为key的名称
     * @return
     */
    @Cacheable(value = {"menus"}, key = "#root.methodName")
    @Override
    public List<Menu> queryMenu() {
        System.out.println("该方法被调用");
        List<Menu> menuList = menuDao.selectList();
        return menuList.stream().filter(menu -> menu.getParentId() == 0).peek(menu ->
                menu.setChildren(menuList.stream().filter(menuChild -> menuChild.getParentId() == menu.getMenuId()).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    /**
     * 功能：生成树形菜单并将菜单保存到redis缓存中
     * 问题：可能大量请求访问同一个key，但key此时失效，造成缓存击穿
     * 解决：添加本地锁，只允许一个请求访问数据库，拿到数据后保存到redis缓存中，其他请求只从redis中获取数据
     * @return
     */
    public synchronized List<Menu> genMenuList() {
        String menusFromRedis = redisTemplate.opsForValue().get("menus");
        if (!StringUtils.isNullOrEmpty(menusFromRedis)){
            return JSON.parseObject(menusFromRedis, new TypeReference<List<Menu>>(){});
    }
    List<Menu> menuList = menuDao.selectList();
    List<Menu> menus = menuList.stream().filter(menu -> menu.getParentId() == 0).peek(menu ->
            menu.setChildren(menuList.stream().filter(menuChild -> menuChild.getParentId() == menu.getMenuId()).collect(Collectors.toList()))
    ).collect(Collectors.toList());
        redisTemplate.opsForValue().set("menus", JSON.toJSONString(menus));
        return menus;
    }
}
