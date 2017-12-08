package com.tazine.supreweb.core.util;

/**
 * 类型转换工具类
 *
 * @author Frank
 * @since 1.0.0
 */
public class CastUtil {


    public static String cast2String(Object obj){
        return cast2String(obj, "");
    }

    public static String cast2String(Object obj, String defaultValue){
        return null != obj ? String.valueOf(obj) : defaultValue;
    }

    public static Integer cast2Int(){
        return null;
    }

}
