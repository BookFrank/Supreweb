package com.supre.framework;

import com.supre.framework.ioc.IocContainer;
import com.supre.framework.ioc.MyClassLoader;
import com.supre.framework.route.Router;

/**
 * 框架启动类
 *
 * @author frank
 * @since 1.0.0
 */
public class SupremeBootstrap {

    /**
     * 第一次访问类时就会加载其 static 块，start() 是为了让加载更加集中
     */
    public static void start() {
        Class<?>[] initClasses = {Router.class, IocContainer.class};

        for (Class<?> cls : initClasses) {
            MyClassLoader.loadClass(cls.getName(), true);
        }
    }

    public static void main(String[] args) {

        start();
    }
}
