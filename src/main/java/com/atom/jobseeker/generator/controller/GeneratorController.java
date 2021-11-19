package com.atom.jobseeker.generator.controller;

import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.generator.service.GeneratorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wayan
 */
@RestController
@RequestMapping("/generate")
public class GeneratorController {

    @Resource
    private GeneratorService generatorService;

    /**
     * 将处理后的数据存入pos_company和pos_job表
     * @return
     */
    @RequestMapping("/post")
    public R postGenerator(){
        generatorService.genPost();
        return R.ok();
    }

    /**
     * 将处理后的数据存入attr_region
     * @return
     */
    @RequestMapping("/attr")
    public R attrGenerator(){
        generatorService.genAttr();
        return R.ok();
    }


}
