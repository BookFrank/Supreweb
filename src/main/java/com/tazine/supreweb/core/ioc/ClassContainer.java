package com.tazine.supreweb.core.ioc;

import com.tazine.supreweb.core.annotation.Controller;
import com.tazine.supreweb.core.annotation.Service;
import com.tazine.supreweb.core.env.ConfigConstant;
import com.tazine.supreweb.core.env.Environment;

import java.util.HashSet;
import java.util.Set;

/**
 * Class容器
 *
 * @author frank
 * @since 1.0.0
 */
public class ClassContainer {

    private static final Set<Class<?>> classes = new HashSet<Class<?>>();

    static {
        classes.addAll(MyClassLoader.getClassesSet(Environment.getProperty(ConfigConstant.APP_BASE_PACKAGE)));
    }

    public static Set<Class<?>> getAllClasses(){
        return classes;
    }

    public static Set<Class<?>> getControllerClasses(){
        Set<Class<?>> controllers = new HashSet<Class<?>>();
        for (Class<?> clz : classes){
            if (clz.isAnnotationPresent(Controller.class)){
                controllers.add(clz);
            }
        }
        return controllers;
    }

    public static Set<Class<?>> getServiceClasses(){
        Set<Class<?>> services = new HashSet<Class<?>>();
        for (Class<?> clz : classes){
            if (clz.isAnnotationPresent(Service.class)){
                services.add(clz);
            }
        }
        return services;
    }

    public static void main(String[] args) {


        for (Class<?> c : getControllerClasses()){
            System.out.println(c.getSimpleName());
        }

    }


}

