package com.supre.framework.route;

import com.supre.framework.util.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 *
 * @author frank
 * @since 1.0.0
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getLong(String name){
        return CastUtil.cast2Long(paramMap.get(name));
    }

    public String getParameter(String key){
        return (String) paramMap.get(key);
    }

    public Map<String,Object> getMap(){
        return paramMap;
    }
}
