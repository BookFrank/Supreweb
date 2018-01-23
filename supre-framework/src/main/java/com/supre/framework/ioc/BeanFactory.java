package com.supre.framework.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean 容器
 *
 * @author frank
 * @since 1.0.0
 */
public class BeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    /**
     * Bean 映射（用于存放 Bean 类与 Bean 实例的映射关系）
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClasses = ClassContainer.getAllClasses();
        for (Class<?> clz : beanClasses){
            BEAN_MAP.put(clz, ReflectionUtil.newInstance(clz));
        }
    }

    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by this class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }



}
