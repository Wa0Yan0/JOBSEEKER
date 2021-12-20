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
     * 实例化对象
     */
    public static R ok() {
        return new R();
    }

    /**
     * 成功，封装数据
     *
     * @param key
     * @param value
     * @return
     */
    public R wrapper(String key, Object value) {
        put(key, value);
        return this;
    }

    /**
     * 错误，设置状态码和错误信息
     * @param code
     * @param msg
     * @return
     */
    public static R error(int code, String msg) {
        return R.ok().wrapper("code", code).wrapper("msg", msg);
    }
}