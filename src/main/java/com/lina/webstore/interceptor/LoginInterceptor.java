package com.lina.webstore.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse respanse, Object handler ) throws Exception{
        
        HttpSession session = request.getSession();
        Object obj=session.getAttribute("uid");
        if(obj==null){
            respanse.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
    
}
