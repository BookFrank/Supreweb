package com.supre.web;

import com.supre.framework.ioc.ClassContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Test
 *
 * @author frank
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        for (Class<?> c : ClassContainer.getControllerClasses()) {
            System.out.println(c.getSimpleName());
            try {
                Object object = c.newInstance();
                Method method = c.getDeclaredMethod("hello");
                method.invoke(object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
