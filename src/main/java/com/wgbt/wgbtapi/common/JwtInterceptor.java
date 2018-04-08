package com.wgbt.wgbtapi.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wgbt.wgbtapi.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        final String token = request.getHeader(HEADER_AUTH); // Authorization 는 자기 토큰

        if(token != null && jwtService.isUsable(token)){
            return true;
        }else{
            throw new UnauthorizedException();
        }

    }
}