package com.atom.jobseeker.rent.service.impl;

import com.atom.jobseeker.attr.dao.AttrDao;
import com.atom.jobseeker.attr.pojo.Region;
import com.atom.jobseeker.rent.dao.CommunityDao;
import com.atom.jobseeker.rent.pojo.Community;
import com.atom.jobseeker.rent.utils.IPage;
import com.atom.jobseeker.rent.utils.PageUtils;
import com.atom.jobseeker.rent.vo.HouseVo;
import com.atom.jobseeker.rent.vo.QueryVo;
import com.atom.jobseeker.rent.dao.HouseDao;
import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.rent.service.HouseService;
import com.atom.jobseeker.search.es.HouseEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author SunLei
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private CommunityDao communityDao;

    /**
     * 根据单个ID搜索到对应的租房信息
     * @param hId
     * @return 返回house实例对象
     */
    @Override
    public HouseVo queryHouseById(Long hId,List<Region> regions) {
        StringBuffer stringBuffer=new StringBuffer();
        House house = houseDao.selectOneById(hId);
        HouseVo houseVo=new HouseVo(house);
        houseVo.setFields(house);
        houseVo.setHosRegion(getRegion(stringBuffer,regions,house.getCityId(),house.getRegionId()).toString());
        return houseVo;
    }

    /**
     * 将搜索分页好的House实例对象封装到分页对象的列表中
     * @param params
     * @return 返回分页好的实例对象，对象包含House实例对象列表
     */
    @Override
    public PageUtils queryHousesInforWithPage(Map<String, Object> params,List<Region> regions) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer stringBuffer=new StringBuffer();
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
        List<HouseVo> houseVos = houses.stream().map(hos -> {
            HouseVo houseVo = new HouseVo(hos);
            houseVo.setHosRegion(getRegion(stringBuffer, regions, hos.getCityId(), hos.getRegionId()).toString());
            return houseVo;
        }).collect(Collectors.toList());
        //将信息列表封装到page工具类并返回页面
        pageUtils.setList(houseVos);
        return pageUtils;
    }

    @Override
    public Long[] filterIds(Long[] ids,String methodName) {
        return "up".equals(methodName)
                ? Arrays.stream(ids).filter(id -> houseDao.selectStatus(id) == 0).toArray(Long[]::new)
                : Arrays.stream(ids).filter(id -> houseDao.selectStatus(id) == 1).toArray(Long[]::new);
    }

//    @Override
//    public List<HouseEs> genHouseEsList(Long[] ids) {
//        //根据当前通过id获取house体信息
//        List<House> houseList = houseDao.selectHouseList(ids);
//        return houseList.stream().map(house -> {
//            Community community = communityDao.selectOneById(house.getCmyId());
//            HouseEs houseEs = new HouseEs(house, community);
//            houseEs.setHosStyle(house.getHosStyle().replaceAll("\\u00A0"," ").split(" +")[0]);
//            return houseEs;
//        }).collect(Collectors.toList());
//    }

    @Override
    public void updateBathIssueStatus(Long[] id, short status) {

    }

    @Override
    public int updateCmyId(Long oldId, Long newId) {
        return houseDao.updateCmyId(oldId, newId);
    }

    @Override
    public int updateRegion(Long cId, Long rId, Long cmyId) {
        return houseDao.updateRegion(cId,rId,cmyId);
    }

    @Override
    public int delNewHouseByCmyId(List<Long> ids) {
        return houseDao.delNewHouseByCmyId(ids);
    }

    @Override
    public int updateOneHouse(House house) {
        return houseDao.updateOneHouse(house);
    }

    @Override
    public int addNewHouse(House house) {
        int i = houseDao.insertHouse(house);
        if (i>0){
            i=houseDao.insertNewHouse(house);
            if (i>0){
                houseDao.delHouseById(house.getHosId());
            }
        }
        return i;
    }

    @Override
    public int deleteNewHouseByIds(List<Long> ids) {
        return houseDao.delNewHouseByIds(ids);
    }

    @Override
    public StringBuffer getRegion(StringBuffer stringBuffer, List<Region> regions, Long cityId, Long regionId) {
        if(stringBuffer.length()!=0){
            stringBuffer.delete(0,stringBuffer.length());
        }
        regions.stream().filter(region -> region.getId()==cityId).forEach(region -> {
            stringBuffer.append(region.getName()+"-");
            List<Region> children = region.getChildren();
            children.stream().filter(chil -> chil.getId()==regionId).forEach(ch->{
                stringBuffer.append(ch.getName());
            });
        });
        return stringBuffer;
    }


}
