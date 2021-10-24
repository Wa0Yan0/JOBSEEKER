package com.atom.jobseeker;

import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.pojo.Job;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JobseekerApplicationTests {

    @Resource
    private JobDao jobDao;

    @Test
    void contextLoads() {
        System.out.println(jobDao.selectOneById(51371417L));
    }

}
