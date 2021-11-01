package com.atom.jobseeker;

import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.search.es.JobEs;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class JobseekerApplicationTests {

    @Resource
    private JobDao jobDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

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

    @Test
    void redisTest(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("uuid", UUID.randomUUID().toString());
        String uuid = ops.get("uuid");
        System.out.println(uuid);
    }

}
