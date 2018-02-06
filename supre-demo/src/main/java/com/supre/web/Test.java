package com.supre.web;

import com.supre.framework.ioc.ClassContainer;

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
        }
    }
}
