package com.tazine.supreweb.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 类型转换工具类
 *
 * @author frank
 * @since 1.0.0
 */
public class CastUtil {


    public static String cast2String(Object obj) {
        return cast2String(obj, "");
    }

    public static String cast2String(Object obj, String defaultValue) {
        return null != obj ? String.valueOf(obj) : defaultValue;
    }

    public static int cast2Int(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (null != obj) {
            String str = cast2String(obj);
            if (StringUtils.isNotEmpty(str)) {
                try {
                    intValue = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    public static int cast2Int(Object obj){
        return cast2Int(obj, 0);
    }


    public static long cast2Long(Object o) {
        return cast2Long(o,0L);
    }

    public static long cast2Long(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (null != obj) {
            String str = cast2String(obj);
            if (StringUtils.isNotEmpty(str)) {
                try {
                    longValue = Long.parseLong(str);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }
}
