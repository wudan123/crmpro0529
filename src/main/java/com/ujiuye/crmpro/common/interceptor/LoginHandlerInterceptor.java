package com.ujiuye.crmpro.common.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        if (uri.indexOf("login")>0 ||uri.indexOf("css")>0 ||uri.indexOf("js")>0 || uri.indexOf("img")>0 ||
                uri.indexOf("fonts")>0){

            return true;

        }
        HttpSession session = request.getSession();
        Object login = session.getAttribute("LOGIN");
        if (login!=null){
            return true;
        }

        response.sendRedirect("/login.jsp");

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
