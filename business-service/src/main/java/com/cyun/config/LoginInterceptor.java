package com.cyun.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    //在请求处理之前进行调用（Controller方法调用之前
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = (String) httpServletRequest.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                Map<String, Object> data = new HashMap<>();
                data.put("code", HttpStatus.UNAUTHORIZED.value());
                data.put("msg", "请先登录!");
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setContentType("text/json");
                PrintWriter printWriter = httpServletResponse.getWriter();
                printWriter.write(JSONObject.toJSONString(data));
                printWriter.flush();
                printWriter.close();
                return false;
            }
        }
        return true;
    }
}
