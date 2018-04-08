package com.wgbt.wgbtapi.config;

import com.wgbt.wgbtapi.common.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final String[] EXCLUDE_PATHS = {
            "/api/user/make",
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
                .allowCredentials(false)
                .maxAge(3000);
//                        .allowedHeaders("application/json")
//                        .exposedHeaders("header1", "header2")
    }
}

//@Configuration
//public class WebConfig {
//
//    //Global CORS 설정
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("PUT", "DELETE", "GET", "POST")
//                        .allowCredentials(false)
//                        .maxAge(3000);
//                        .allowedHeaders("application/json")
//                        .exposedHeaders("header1", "header2")
//
//            }
//        };
//    }
//
//}
