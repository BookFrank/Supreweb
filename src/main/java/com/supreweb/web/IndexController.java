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
    public void hello(){
        System.out.println("Hello World");
    }

}
