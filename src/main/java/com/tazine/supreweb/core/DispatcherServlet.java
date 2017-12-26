package com.tazine.supreweb.core;

import com.tazine.supreweb.core.annotation.RequestMethod;
import com.tazine.supreweb.core.ioc.BeanFactory;
import com.tazine.supreweb.core.ioc.ReflectionUtil;
import com.tazine.supreweb.core.route.RequestHandler;
import com.tazine.supreweb.core.route.Request;
import com.tazine.supreweb.core.route.Router;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * DispatcherServlet
 *
 * @author frank
 * @since 1.0.0
 */
@WebServlet(value = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 框架初始化
        SupremeBootstrap.start();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestMethod requestMethod = RequestMethod.valueOf(req.getMethod().toUpperCase());
        String path = req.getPathInfo();
        Request request = new Request(requestMethod, path);

        RequestHandler requestHandler = Router.getHandler(request);
        if (null != requestHandler){

            Class<?> controllerCls = requestHandler.getControllerClass();
            Object controller = BeanFactory.getBean(controllerCls);
            Method method = requestHandler.getMethod();


            String ret = (String) ReflectionUtil.invokeMethod(controller, method);
            resp.setStatus(200);
            PrintWriter writer = resp.getWriter();
            writer.write(ret);
            writer.close();
        }else {
            errorBack(resp);
        }
    }

    private void errorBack(HttpServletResponse response){
        response.setStatus(404);
    }
}
