package com.atom.jobseeker.system.controller;

import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.system.pojo.Menu;
import com.atom.jobseeker.system.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wayan
 */

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/list")
    public R list(){
        List<Menu> menuList = menuService.genMenuList();
        return R.ok().wrapper("menus", menuList);
    }
}
