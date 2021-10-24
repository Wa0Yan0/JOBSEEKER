package com.atom.jobseeker.common.utils;

import java.util.HashMap;

/**
 * @author wayan
 */
public class R extends HashMap<String, Object> {

    /**
     * 空参构造函数，初始化数据
     */
    public R() {
        put("code", 0);
        put("msg", "success");
    }

    /**
     * 跟新、保存、删除成功，直接返回初始化值
     */
    public static R ok() {
        return new R();
    }

    /**
     * 查询成功，封装数据
     *
     * @param key
     * @param value
     * @return
     */
    public R wrapper(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 错误返回，状态码和错误信息
     * @param code
     * @param msg
     * @return
     */
    public static R error(String code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
}