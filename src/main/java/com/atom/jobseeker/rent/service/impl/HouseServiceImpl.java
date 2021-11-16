package com.atom.jobseeker.rent.service.impl;

import com.atom.jobseeker.post.pojo.Company;
import com.atom.jobseeker.post.pojo.Job;
import com.atom.jobseeker.post.vo.CheckVo;
import com.atom.jobseeker.rent.dao.CommunityDao;
import com.atom.jobseeker.rent.utils.IPage;
import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.rent.vo.QueryVo;
import com.atom.jobseeker.rent.dao.HouseDao;
import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.rent.service.HouseService;
import com.atom.jobseeker.search.es.JobEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseDao houseDao;

    /**
     * 根据单个ID搜索到对应的租房信息
     * @param hId
     * @return 返回house实例对象
     */
    @Override
    public House queryHouseById(Long hId) {
        House house = houseDao.selectOneById(hId);
        return house;
    }

    /**
     * 将搜索分页好的House实例对象封装到分页对象的列表中
     * @param params
     * @return 返回分页好的实例对象，对象包含House实例对象列表
     */
    @Override
    public PageUtils queryHousesInforWithPage(Map<String, Object> params) {
        //将高级查询信息封装到高级查询类
        QueryVo queryVo = new QueryVo(params);
        //将分页信息封装到分页类
        IPage iPage = new IPage(params);
        //根据查询到的总记录数封装到分页类的总记录数中
        iPage.setTotalCount(queryVo.hasQuery() ? houseDao.selectTotalCountWithQuery(queryVo) : houseDao.selectTotalCount());
        //将分页类封装到page工具类
        PageUtils pageUtils = new PageUtils(iPage);
        //根据查询条件查询到信息列表
        List<House> houses = houseDao.selectListWithQuery(queryVo, iPage.getBegin(), iPage.getPageSize());
        //将信息列表封装到page工具类并返回页面
        pageUtils.setList(houses);
        return pageUtils;
    }

    @Override
    public Long[] filterIds(CheckVo checkVo) {
        //初始化当前id列表
        ArrayList<Long> ids = new ArrayList<>();
        //遍历参数中id
        for (Long id : checkVo.getIds()) {
            //elasticsearch上线分支
            if (checkVo.getStatus() != null) {
                if ("通过".equals(checkVo.getStatus())) {
                    String issueStatus = houseDao.selectStatus(id);
                    //防止通过的数据重复更新，过滤已经通过的
                    if (!"通过".equals(issueStatus)) {
                        ids.add(id);
                    }
                }
            }
            //elasticsearch下线分支
            else {
                String issueStatus = houseDao.selectStatus(id);
                if ("通过".equals(issueStatus)) {
                    ids.add(id);
                }
            }
        }
        return ids.toArray(new Long[0]);
    }

    @Override
    public List<JobEs> genJobEsList(Long[] ids) {
        //根据当前通过id获取house体信息
        List<House> jobList = houseDao.selectHouseList(ids);
        return jobList.stream().map(job -> {
            Company company = CommunityDao(job.getCompanyId());
            JobEs jobEs = new JobEs(job, company);
            jobEs.setSalary(handleSalary(jobEs.getSalaryText()));
            return jobEs;
        }).collect(Collectors.toList());
    }


}
