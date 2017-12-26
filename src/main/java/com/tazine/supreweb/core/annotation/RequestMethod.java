package com.tazine.supreweb.core.annotation;

/**
 * HTTP RequestMethod
 * @author frank
 * @since 1.0.0
 */
public enum RequestMethod {

    GET(1,"GET"),
    POST(2,"POST");

    private int code;
    private String method;

    RequestMethod(int code, String method) {
        this.code = code;
        this.method = method;
    }
}
