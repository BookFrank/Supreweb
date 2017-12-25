package com.tazine.supreweb.core;

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

    private static Set<Class<?>> classes = new HashSet<Class<?>>();

    static {
        classes = MyClassLoader.getClassesSet(Environment.getProperty(ConfigConstant.APP_BASE_PACKAGE));
    }



}

