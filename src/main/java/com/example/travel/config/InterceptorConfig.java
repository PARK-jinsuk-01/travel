package com.example.travel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.travel.interceptor.SignInCheckInterceptor;

// @Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private SignInCheckInterceptor signInCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInCheckInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/signin", "/signup","/", "/cssjs/**", "/img/**"
          );
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
