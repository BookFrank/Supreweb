package com.tazine.supreweb.core.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类：实例化Bean
 *
 * @author frank
 * @since 1.0.0
 */
public class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls) {
        Object obj = null;
        try {
            obj = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("cls.newInstance() error");
            e.printStackTrace();
        }
        return obj;
    }

    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result = null;
        try {
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("method.invoke() error");
            e.printStackTrace();
        }
        return result;
    }

    public static void setField(Object obj, Field field, Object val) {
        field.setAccessible(true);
        try {
            field.set(obj, val);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
