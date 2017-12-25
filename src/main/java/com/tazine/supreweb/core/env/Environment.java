package com.tazine.supreweb.core.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 系统环境信息
 *
 * @author Frank
 * @since 1.0.0
 */
public class Environment {

    private static final Properties properties = new Properties();

    private static final Logger logger = LoggerFactory.getLogger(Environment.class);

    static {
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(ConfigConstant.CONFIG_FILE);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error when load config file", e);
        } finally {
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("Error when close Inputstream", e);
                }
            }
        }
    }

    public static String getProperty(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }

    public static String getProperty(String key){
        return properties.getProperty(key, "");
    }

}
