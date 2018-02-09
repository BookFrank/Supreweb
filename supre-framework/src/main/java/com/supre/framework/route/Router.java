package com.supre.framework.route;

import com.supre.framework.annotation.RequestMapping;
import com.supre.framework.annotation.RequestMethod;
import com.supre.framework.ioc.ClassContainer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Framework Router
 * 根据控制器方法注解生成 RouterMap，供调用
 *
 * @author frank
 * @since 1.0.0
 */
public class Router {

    private static final Map<Request, RequestHandler> ROUTER_MAP = new HashMap<Request, RequestHandler>();

    static {
        Set<Class<?>> controllers = ClassContainer.getControllerClasses();

        for (Class<?> cls : controllers) {
            Method[] methods = cls.getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(RequestMapping.class)) {
                    System.out.println(m.getName() + " add in RouterMap");
                    RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
                    RequestMethod requestMethod = requestMapping.method();
                    String path = requestMapping.value();

                    Request request = new Request(requestMethod, path);
                    RequestHandler requestHandler = new RequestHandler(cls, m);
                    ROUTER_MAP.put(request, requestHandler);
                }
            }
        }
    }

    public static RequestHandler getHandler(Request request) {
        return ROUTER_MAP.get(request);
    }
}
