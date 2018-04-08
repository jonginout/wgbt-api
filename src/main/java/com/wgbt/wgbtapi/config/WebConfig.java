package com.wgbt.wgbtapi.config;

import com.wgbt.wgbtapi.common.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String[] EXCLUDE_PATHS = {
            "/api/user/logout",
            "/api/user/login",
            "/api/user/me",
            "/error/**"
    };

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("Content-Type", "Authorization")
                .exposedHeaders("Content-Type", "Authorization")
                .maxAge(3000);
    }
}