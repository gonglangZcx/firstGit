package com.cuit.house.interceptor;

import com.cuit.house.pojo.User;
import com.cuit.house.uitls.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Component
public class AuthActionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = UserContext.getUser();
        if(user==null){
            String msg = URLEncoder.encode("请先登录", "utf-8");
            String target = URLEncoder.encode(request.getRequestURL().toString(), "utf-8");
            if("GET".equalsIgnoreCase(request.getMethod())){
                response.sendRedirect("/accounts/signin?errorMsg="+msg+"&target="+target);
                return false;
            }else{
                response.sendRedirect("/accounts/signin?errorMsg="+msg);
                return false;
            }
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
