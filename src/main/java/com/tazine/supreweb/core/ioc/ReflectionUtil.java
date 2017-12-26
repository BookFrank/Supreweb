package com.tazine.supreweb.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类：实例化Bean
 *
 * @author frank
 * @since 1.0.0
 */
public class ReflectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> clz){
        Object obj = null;
        try {
            obj = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Object invokeMethod(Object obj, Method method, Object ...args){
        Object result = null;
        try {
            method.invoke(obj,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void setField(Object obj, Field field, Object val){
        field.setAccessible(true);
        try {
            field.set(obj, val);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
