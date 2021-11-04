package com.atom.jobseeker.system.controller;

import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.system.pojo.Menu;
import com.atom.jobseeker.system.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wayan
 */

@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/list")
    public R list(){
        List<Menu> menuList = menuService.queryMenu();
        return R.ok().wrapper("menus", menuList);
    }

    @RequestMapping("/parentMenus")
    public R parentMenuList(){
        List<Menu> parentMenus = menuService.queryParentMenus();
        return R.ok().wrapper("parentMenus", parentMenus);
    }

    @RequestMapping("/{menuId}")
    public R menuInfo(@PathVariable("menuId") Long menuId){
        Menu menu = menuService.queryOneById(menuId);
        return R.ok().wrapper("menu", menu);
    }

    @RequestMapping("/save")
    public R save(@RequestBody Menu menu){
        menuService.save(menu);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody Menu menu){
        menuService.update(menu);
        return R.ok();
    }

    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
        menuService.delete(id);
        return R.ok();
    }
}
