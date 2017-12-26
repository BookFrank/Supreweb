package com.tazine.supreweb.core.route;

import com.tazine.supreweb.core.annotation.RequestMapping;
import com.tazine.supreweb.core.annotation.RequestMethod;
import com.tazine.supreweb.core.ioc.ClassContainer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lina on 2017/12/26.
 *
 * @author frank
 * @since 1.0.0
 */
public class Router {

    private static final Map<Request,RequestHandler> routerMap = new HashMap<Request, RequestHandler>();

    static {
        Set<Class<?>> controllers = ClassContainer.getControllerClasses();

        for (Class<?> cls : controllers){
            Method[] methods = cls.getDeclaredMethods();
            for (Method m : methods){
                if (m.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
                    RequestMethod requestMethod = requestMapping.method();
                    String path = requestMapping.value();

                    Request request = new Request(requestMethod,path);
                    RequestHandler requestHandler = new RequestHandler(cls, m);
                    routerMap.put(request, requestHandler);
                }
            }
        }
    }

    public static RequestHandler getHandler(Request request){
        return routerMap.get(request);
    }


}
