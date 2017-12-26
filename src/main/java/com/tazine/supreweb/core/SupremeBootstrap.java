package com.tazine.supreweb.core;

import com.supreweb.web.IndexController;
import com.tazine.supreweb.core.annotation.RequestMethod;
import com.tazine.supreweb.core.ioc.BeanFactory;
import com.tazine.supreweb.core.ioc.MyClassLoader;
import com.tazine.supreweb.core.ioc.ReflectionUtil;
import com.tazine.supreweb.core.ioc.IocContainer;
import com.tazine.supreweb.core.route.RequestHandler;
import com.tazine.supreweb.core.route.Request;
import com.tazine.supreweb.core.route.Router;

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
    public static void start(){
        Class<?>[] initClasses = {Router.class, IocContainer.class};

        for (Class<?> cls : initClasses){
            MyClassLoader.loadClass(cls.getName(), true);
        }
    }

    public static void main(String[] args) {

        start();

        Request request = new Request(RequestMethod.GET, "/hello");

        RequestHandler requestHandler = Router.getHandler(request);

        String a = (String) ReflectionUtil.invokeMethod(BeanFactory.getBean(requestHandler.getControllerClass()), requestHandler.getMethod());

        System.out.println(a + "--");

        IndexController controller = BeanFactory.getBean(IndexController.class);

        controller.service.say();

        RequestMethod requestMethod = RequestMethod.valueOf("GET");
        System.out.println(requestMethod.name());

    }

}
