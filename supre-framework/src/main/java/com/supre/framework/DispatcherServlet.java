package com.supre.framework;

import com.alibaba.fastjson.JSON;
import com.supre.framework.annotation.RequestMethod;
import com.supre.framework.env.ConfigConstant;
import com.supre.framework.ioc.BeanFactory;
import com.supre.framework.ioc.ReflectionUtil;
import com.supre.framework.response.Data;
import com.supre.framework.response.View;
import com.supre.framework.route.Param;
import com.supre.framework.route.Request;
import com.supre.framework.route.RequestHandler;
import com.supre.framework.route.Router;
import com.supre.framework.util.CodecUtil;
import com.supre.framework.util.StreamUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * DispatcherServlet
 *
 * @author frank
 * @date 2018/01/23
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
        String requestPath = req.getPathInfo();
        Request request = new Request(requestMethod, requestPath);

        RequestHandler requestHandler = Router.getHandler(request);
        if (null != requestHandler) {
            // 获取控制器 Class对象及其 Controller Bean实例
            Class<?> controllerCls = requestHandler.getControllerClass();
            Object controller = BeanFactory.getBean(controllerCls);

            // 创建请求参数对象
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }

            String body = CodecUtil.decodeUrl(StreamUtil.getString(req.getInputStream()));
            if (StringUtils.isNotEmpty(body)) {
                String[] params = body.split("&");
                if (null != params && params.length > 0) {
                    for (String param : params) {
                        String[] array = param.split("=");
                        if (null != array && array.length == 2) {
                            String k = array[0];
                            String v = array[1];
                            paramMap.put(k, v);
                        }
                    }
                }
            }

            Param param = new Param(paramMap);
            // 反射调用方法

            Method method = requestHandler.getMethod();
            Object result = ReflectionUtil.invokeMethod(controller, method, param);
            if (result instanceof View) {
                System.out.println("View 类型");
                // 返回 JSP 页面
                View view = (View)result;
                String path = view.getPath();
                if (StringUtils.isNotEmpty(path)) {
                    if (path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigConstant.APP_JSP_PATH + path).forward(req, resp);
                    }
                }
            } else if (result instanceof Data) {
                // 返回 JSON 数据
                Data data = (Data)result;
                Object model = data.getModel();
                if (null != model) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    writer.write(JSON.toJSONString(model));
                    writer.flush();
                    writer.close();
                }
            } else {
                System.out.println("未知类型");
            }
        } else {
            resp.sendError(404, "Method not found");
        }
    }
}
