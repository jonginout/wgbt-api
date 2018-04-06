package com.wgbt.wgbtapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig {

    //Global CORS 설정
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("PUT", "DELETE", "GET", "POST")
                        .allowCredentials(false)
                        .maxAge(3000);
//                        .allowedHeaders("application/json")
//                        .exposedHeaders("header1", "header2")

            }
        };
    }

}
