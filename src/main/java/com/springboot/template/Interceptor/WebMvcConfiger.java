package com.springboot.template.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jinyu on 2018/8/30.
 */
@Configuration
public class WebMvcConfiger extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserHandlerInterceptor()).addPathPatterns("/test");
    }

    public static UserHandlerInterceptor getUserHandlerInterceptor(){
        return new UserHandlerInterceptor();
    }
}
