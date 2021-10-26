package com.atom.jobseeker;

import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.search.es.JobEs;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JobseekerApplicationTests {

    @Resource
    private JobDao jobDao;

    @Resource
    private JobService jobService;

    @Resource
    private RestHighLevelClient esClient;

    @Test
    void contextLoads() {
        JobEs jobEs = jobService.genJobEs(114351420L);
        System.out.println(jobEs.toString());
    }

    @Test
    void esClientTest() {
        System.out.println(esClient);

    }

}
