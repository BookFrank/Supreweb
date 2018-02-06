package com.supre.web.controller;

import com.supre.framework.annotation.Autowired;
import com.supre.framework.annotation.Controller;
import com.supre.framework.annotation.RequestMapping;
import com.supre.framework.annotation.RequestMethod;
import com.supre.framework.response.Data;
import com.supre.web.service.DemoService;

/**
 * DemoController
 *
 * @author frank
 * @since 1.0.0
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Data hello() {
        String res = demoService.sayHello();
        System.out.println("Hello World");
        return new Data(res);
    }
}
