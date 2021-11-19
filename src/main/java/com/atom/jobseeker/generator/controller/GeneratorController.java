package com.atom.jobseeker.generator.controller;

import com.atom.jobseeker.common.constant.ErrorEnum;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.generator.service.GeneratorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wayan,sunlei
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
     * 将处理后的数据存入attr_region和attr_major
     * @return
     */
    @RequestMapping("/attr")
    public R attrGenerator(){
        generatorService.genAttr();
        return R.ok();
    }

    /**
     * 将小区重复的信息进行删除并修改对应的租房表，然后分别推送到正式表中
     * @return
     */
    @RequestMapping("/rent")
    public R duplicateRemoval(){
        int dupCmy = generatorService.dupCmyHandle();
        if (dupCmy==1){
            generatorService.postCmyNewData();
            generatorService.postHouseNewData();
            generatorService.delHouse();
            generatorService.delCmy();
            return R.ok();
        }else {
            return R.error(ErrorEnum.GEN_DUP_HANDLE_ERROR.getCode(),ErrorEnum.GEN_DUP_HANDLE_ERROR.getMsg());
        }
    }


}
