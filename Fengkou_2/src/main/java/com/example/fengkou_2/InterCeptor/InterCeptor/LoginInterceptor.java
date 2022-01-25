package com.example.fengkou_2.InterCeptor.InterCeptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前，执行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        Object username = session.getAttribute("username");

        if (username != null){
           //放行
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }

}
