package com.atom.jobseeker;

import com.alibaba.fastjson.JSON;
import com.atom.jobseeker.attr.dao.AttrDao;
import com.atom.jobseeker.attr.pojo.City;
import com.atom.jobseeker.attr.pojo.Major;
import com.atom.jobseeker.attr.pojo.Region;
import com.atom.jobseeker.attr.service.AttrService;
import com.atom.jobseeker.common.constant.IssueStatus;
import com.atom.jobseeker.post.dao.CompanyDao;
import com.atom.jobseeker.post.dao.JobDao;
import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.service.JobService;
import com.atom.jobseeker.search.config.ElasticSearchConfig;
import com.atom.jobseeker.search.constant.EsConstant;
import com.atom.jobseeker.search.es.JobEs;
import com.atom.jobseeker.system.pojo.Menu;
import com.atom.jobseeker.system.service.MenuService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class JobseekerApplicationTests {

    @Resource
    private JobDao jobDao;

    @Resource
    private JobService jobService;

    @Resource
    private RestHighLevelClient esClient;

    @Resource
    private AttrDao attrDao;

    @Resource
    private MenuService menuService;

    @Resource
    private CompanyDao companyDao;

    @Resource
    private AttrService attrService;

    @Test
    void contextLoads() {
        Map<String, Object> params = new HashMap<>();  //定义Map集合
        params.put("apple","新鲜的苹果");  //向集合中添加元素
        params.put("computer","配置优良的计算机");
        params.put("book","堆积成山的图书");
        List<String> keys = params.keySet().stream().map(String::toString).collect(Collectors.toList());
        System.out.println(keys);
    }

    @Test
    void esClientTest() throws IOException {
        CountRequest countRequest = new CountRequest().indices(EsConstant.JOB_INDEX);
        CountResponse countResponse = esClient.count(countRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(countResponse.getCount());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery()).from(0).size(30);
        SearchRequest searchRequest = new SearchRequest().indices(EsConstant.JOB_INDEX).source(searchSourceBuilder);
        SearchResponse searchResponse = esClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
        List<JobEs> jobEsList = Arrays.stream(searchResponse.getHits().getHits()).map(hit -> {
            String source = hit.getSourceAsString();
            return JSON.parseObject(source, JobEs.class);
        }).collect(Collectors.toList());
        System.out.println(jobEsList);

    }

    @Test
    void filterTest() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(114351420L);
        ids.add(114351420L);
        ids.add(114351420L);
        Long[] longs = ids.toArray(new Long[0]);
        for (Long aLong : longs) {
            System.out.println(aLong);
        }
    }

    @Test
    void test1() {
        List<Major> majors = attrDao.selectMajorList();
        System.out.println(majors);
    }

    @Test
    void Menu() {
        System.out.println(menuService.queryMenu());
    }

    @Test
    void insert(){
        Company company = companyDao.selectOneById(100614L);
        Long companyId = companyDao.insert(company);
        System.out.println(companyId);
        List<Job> jobList = jobDao.selectListByCompanyId(company.getId());
        if (jobList != null) {
            jobList.forEach(job -> {
                job.setCompanyId(companyId);
                System.out.println(job);
                jobDao.insert(job);
            });
        }
    }

    @Test
    void insertReturn(){
        Map<String, String> region = attrDao.selectRegionById(4);
        System.out.println(region.get("cityName"));
    }


}
