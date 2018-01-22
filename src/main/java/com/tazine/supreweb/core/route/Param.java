package com.tazine.supreweb.core.route;

import com.tazine.supreweb.core.util.CastUtil;

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

    public Map<String,Object> getMap(){
        return paramMap;
    }


}
