package com.atom.jobseeker.search.service.impl;

import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.search.service.ElasticJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wayan
 */
@Slf4j
@Service
public class ElasticJobServiceImpl implements ElasticJobService {

    @Override
    public void upToElastic(JobEs jobEs) {
        //TODO 上传到ElasticSearch
        log.info(jobEs.toString());
    }
}
