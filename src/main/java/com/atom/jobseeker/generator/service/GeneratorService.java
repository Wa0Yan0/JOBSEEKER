package com.atom.jobseeker.generator.service;


import java.util.List;

/**
 * @author wayan,sunlei
 */
public interface GeneratorService {

    /**
     * 对company和job表数据进行处理，并存储到pos_company和pos_job1
     */
    void genPost();

    /**
     * 对major、city和location表进行处理,并存储到attr_major和attr_region1
     */
    void genAttr();


    /**
     * 将house和community表的数据进行处理，使community与house形成一对多的关系
     * @return
     */
    int dupCmyHandle();

    /**
     * 将处理后临时表house内的数据推送到正式表rent_house中
     * @return
     */
    int postHouseNewData();

    /**
     * 将处理后临时表community内的数据推送到正式表rent_community中
     * @return
     */
    int postCmyNewData();

    /**
     * 清空house临时表
     * @return
     */
    int delHouse();

    /**
     * 清空community临时表
     * @return
     */
    int delCmy();

}
