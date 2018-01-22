package com.tazine.supreweb.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * CodecUtil ：编码与解码操作工具类
 *
 * @author frank
 * @since 1.0.0
 */
public class CodecUtil {

    private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            logger.error("encode url error, ", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static String decodeUrl(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            logger.error("decode url error, ", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
