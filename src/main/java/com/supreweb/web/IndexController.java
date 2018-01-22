package com.supreweb.web;

import com.tazine.supreweb.core.annotation.Autowired;
import com.tazine.supreweb.core.annotation.Controller;
import com.tazine.supreweb.core.annotation.RequestMapping;
import com.tazine.supreweb.core.annotation.RequestMethod;
import com.tazine.supreweb.core.route.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * IndexController
 *
 * @author frank
 * @since 1.0.0
 */
@Controller
public class IndexController {

    @Autowired
    public IndexService service;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Data hello(String a) {
        System.out.println("Hello World");
        return new Data("hello world");
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public String code() {
        System.out.println(this.hashCode());
        return String.valueOf(this.hashCode());
    }

    public static void main(String[] args) {

        Class<IndexController> clz = IndexController.class;
        IndexController indexController = new IndexController();
        try {
            Method method = clz.getMethod("hello", String.class);
            Data ret = (Data) method.invoke(indexController, new Object());
            System.out.println(ret.getModel());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
