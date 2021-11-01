package com.atom.jobseeker.search.controller;


import com.atom.jobseeker.common.utils.PageUtils;
import com.atom.jobseeker.common.utils.R;
import com.atom.jobseeker.search.service.ElasticJobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wayan
 */
@RestController
public class JobEsController {

    @Resource
    private ElasticJobService elasticJobService;

    @RequestMapping("/api/job/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = elasticJobService.queryPage(params);
        return R.ok().wrapper("page", page);
    }
}
