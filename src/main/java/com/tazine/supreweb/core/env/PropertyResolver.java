package com.tazine.supreweb.core.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性解析
 *
 * @author Frank
 * @since 1.0.0
 */
public class PropertyResolver {

    private static final Logger logger = LoggerFactory.getLogger(PropertyResolver.class);

    /**
     * 根据文件名加载属性文件
     * @param fileName
     * @return
     */
    public static Properties loadProperties(String fileName){
        Properties properties = null;
        InputStream is = null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            logger.error("Error when load config file", e);
        } finally {
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("Error when close Inputstream", e);
                }
            }
        }
        return properties;
    }

//    public static String getProperty(String key, String defaultValue){
//
//    }
//
//    public static String getProperty(){
//
//    }

}
