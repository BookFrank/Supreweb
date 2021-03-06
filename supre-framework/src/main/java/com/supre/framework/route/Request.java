package com.supre.framework.route;

import com.supre.framework.annotation.RequestMethod;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Request
 *
 * @author frank
 * @date 2018/01/23
 */
public class Request {

    private RequestMethod requestMethod;

    private String path;

    public Request(RequestMethod requestMethod, String path) {
        this.requestMethod = requestMethod;
        this.path = path;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public static void main(String[] args) {

        Request r1 = new Request(RequestMethod.GET, "/hello");
        Request r2 = new Request(RequestMethod.GET, "/hello");

        System.out.println(r1 == r2);
        System.out.println(r1.equals(r2));

        Map<Request, Integer> map = new HashMap<Request, Integer>();
        map.put(r1, 32);

        System.out.println(map.get(r1));
        System.out.println(map.get(r2));

    }
}
