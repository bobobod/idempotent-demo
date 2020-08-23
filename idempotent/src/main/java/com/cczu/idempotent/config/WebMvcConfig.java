package com.cczu.idempotent.config;

import com.cczu.idempotent.intercepter.IdempotentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jianzhen.yin
 * @date 2020/8/23
 */
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    IdempotentInterceptor idempotentInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idempotentInterceptor).addPathPatterns("/**");
    }
}
