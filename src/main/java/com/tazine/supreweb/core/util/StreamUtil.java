package com.tazine.supreweb.core.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * StreamUtil
 *
 * @author frank
 * @since 1.0.0
 */
public final class StreamUtil {

    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     *
     * @param is inputStream
     * @return String
     */
    public static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error("error, ", e);
            e.printStackTrace();
        }
        return sb.toString();
    }

}
