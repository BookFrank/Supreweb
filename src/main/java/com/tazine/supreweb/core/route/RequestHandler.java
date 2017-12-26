package com.tazine.supreweb.core.route;

import java.lang.reflect.Method;

/**
 * RequestHandler
 *
 * @author frank
 * @since 1.0.0
 */
public class RequestHandler {

    private Class<?> controllerClass;

    private Method method;

    public RequestHandler(Class<?> controllerClass, Method method) {
        this.controllerClass = controllerClass;
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
