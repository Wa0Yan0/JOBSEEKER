package com.atom.jobseeker.system.dao;

import com.atom.jobseeker.system.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wayan
 */
@Mapper
public interface MenuDao {

    /**
     * 查询全部数据
     * @return
     */
    List<Menu> selectList();
}
