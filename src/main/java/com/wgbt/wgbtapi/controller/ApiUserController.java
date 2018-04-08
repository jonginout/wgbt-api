package com.wgbt.wgbtapi.controller;

import com.wgbt.wgbtapi.domain.User;
import com.wgbt.wgbtapi.service.jwt.JwtService;
import com.wgbt.wgbtapi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

//    @GetMapping("/{no}")
//    public User show(@PathVariable Long no){
//        return userService.detailUser(no);
//    }

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
