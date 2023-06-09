package com.cuit.house.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConf implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;
    @Autowired
    private AuthActionInterceptor authActionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有，因为所有的请求都要看用户有没有登录
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/static").addPathPatterns("/**");
        //拦截想拦截的请求
        registry.addInterceptor(authActionInterceptor)
                .addPathPatterns("/accounts/profile")
                .addPathPatterns("/house/toAdd")
        ;
    }
}
