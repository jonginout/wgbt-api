package com.wgbt.wgbtapi.user.controller;

import com.wgbt.wgbtapi.common.utill.GetJwtToken;
import com.wgbt.wgbtapi.common.utill.HttpSessionUtills;
import com.wgbt.wgbtapi.user.domain.User;
import com.wgbt.wgbtapi.common.service.jwt.JwtService;
import com.wgbt.wgbtapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User loginUser = userService.loginUser(user.getId());

        if(loginUser == null || !loginUser.matchPw(user.getPw())){
            System.out.println("로그인 실패ㅠㅠ");
            map.put("success", false);
            return map;
        }

        String token = jwtService.create("userInfo", loginUser, "user");
        Cookie cookie = new Cookie("token", token);
        if(user.getSave()=="true"){
            int amount = 60 * 60 * 24 * 7; // 일
            cookie.setMaxAge(amount);
        }
        cookie.setPath("/");    // 쿠키를 찾을 경로를 컨텍스트 경로로 변경해 주고...
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        response.setHeader("Authorization", token);

        System.out.println("로그인 성공vv");
        map.put("success", true);
        return map;
    }

    @GetMapping("/me")
    public Map<String, Object> me(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        if(GetJwtToken.getToken(request) == null                        // 토큰 있는지
           || GetJwtToken.getToken(request).length() == 0               // 있어도 옳바른 값이 있는지
           || !jwtService.checkNullToken()){                            // 올바른 서명이 되어있고 값이 제대로 있는 토큰인지
            map.put("success", false);
            return map;
        }

        Long userNo = new Long(jwtService.getUserNo()); // 인티저는 Long으로 변환이 안되기 때문에
        User me = userService.detailUser(userNo);
        if(me == null){
            map.put("success", false);
            return map;
        }

        map.put("success", true);
        map.put("me", me);
        return map;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();

        // 쿠키는 강제로 파괴 불가 / 하지만 유효기간을 0으로 하는 것으로 대신 가능
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    cookie.setPath("/");
                    cookie.setValue("");
                    cookie.setHttpOnly(true);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        map.put("success", true);
        return map;
    }






    @PostMapping("/make")
    public Map<String, Object> signin(@RequestBody User user, HttpServletResponse response){
        User selectedUser = userService.detailUser(user.getNo());
        String token = jwtService.create("userInfo", selectedUser, "user");

        Cookie cookie = new Cookie("token", token);
        // 쿠키를 찾을 경로를 컨텍스트 경로로 변경해 주고...
        cookie.setPath("localhost");
        int amount = 60 * 60 * 24 * 7; // 일
        cookie.setMaxAge(amount);
        response.addCookie(cookie);

        response.setHeader("Authorization", token);
        response.addCookie(cookie);

        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("doc", selectedUser);

        return map;
    }

    @GetMapping("")
    public Map<String, Object> show(){
        Map<String, Object> map = new HashMap<>();
        Long userNo = new Long(jwtService.getUserNo()); // 인티저는 Long으로 변환이 안되기 때문에
        User selectedUser = userService.detailUser(userNo);

        map.put("success", false);
        map.put("doc", selectedUser);
        return map;
    }

}
