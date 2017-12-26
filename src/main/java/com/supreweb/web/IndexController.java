package com.supreweb.web;

import com.tazine.supreweb.core.annotation.Autowired;
import com.tazine.supreweb.core.annotation.Controller;
import com.tazine.supreweb.core.annotation.RequestMapping;
import com.tazine.supreweb.core.annotation.RequestMethod;

/**
 * Created by lina on 2017/12/25.
 *
 * @author frank
 * @since 1.0.0
 */
@Controller
public class IndexController {

    @Autowired
    public IndexService service;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        System.out.println("Hello World");
        return "Hello World!";
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public String code(){
        System.out.println(this.hashCode());
        return String.valueOf(this.hashCode());
    }

}
