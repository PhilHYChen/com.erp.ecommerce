package com.erp.ecommerce.configuration.security.securitycontext.annotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CurrentUserProfileConfiguration implements WebMvcConfigurer {

    @Autowired
    private CurrentUserProfileArgumentResolver<?> currentUserProfileArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserProfileArgumentResolver);
    }
    
}
