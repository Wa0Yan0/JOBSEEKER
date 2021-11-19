package com.atom.jobseeker.generator.service;


/**
 * @author wayan
 */
public interface GeneratorService {

    /**
     * 对company和job表数据进行处理，并存储到pos_company和pos_job
     */
    void genPost();

    /**
     * 对major、city和location表进行处理,并存储到attr_region
     */
    void genAttr();
}
