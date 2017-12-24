package com.tazine.supreweb;

import com.tazine.supreweb.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用启动类
 *
 * @author Frank
 * @since 1.1.0
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        System.out.println(Environment.getProperty("jdbc.driver"));

    }

}
