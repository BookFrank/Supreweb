package com.supre.framework.ioc;

import com.supre.framework.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * IOC 实现类
 *
 * @author frank
 * @since 1.0.0
 */
public class IocContainer {

    private static Map<Class<?>, Object> beanMap = BeanFactory.getBeanMap();

    static {
        Set<Map.Entry<Class<?>, Object>> entrySet = beanMap.entrySet();
        for (Map.Entry<Class<?>, Object> entry : entrySet) {
            di(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 依赖注入
     * @param cls 待注入的class
     * @param object
     */
    private static void di(Class<?> cls, Object object) {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Class<?> beanFieldClass = field.getType();
                Object beanObject = beanMap.get(cls);
                Object fieldObject = beanMap.get(beanFieldClass);
                ReflectionUtil.setField(beanObject, field, fieldObject);
            }
        }
    }
}
