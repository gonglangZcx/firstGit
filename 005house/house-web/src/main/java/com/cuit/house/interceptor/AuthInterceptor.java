package com.cuit.house.interceptor;

import com.cuit.house.constants.CommonConstants;
import com.cuit.house.pojo.User;
import com.cuit.house.uitls.UserContext;
import com.google.common.base.Joiner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //这个地方做的是给前端返回正确和错误的信息，放到域里面，也可以在每个controller里面去做，但是没有必要
        Map<String, String[]> map = request.getParameterMap();
         map.forEach((k,v)->{
             if(k.equals("errorMsg")||k.equals("successMsg")||k.equals("target")){
                 request.setAttribute(k, Joiner.on(",").join(v));
             }
         });
        String requestURI = request.getRequestURI();
        if(requestURI.startsWith("/static")||requestURI.startsWith("/error")){
            return true;
        }
        HttpSession session =request.getSession();
        User user = (User) session.getAttribute(CommonConstants.USER_ATTRIBUTE);
        if(user!=null){
            UserContext.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
