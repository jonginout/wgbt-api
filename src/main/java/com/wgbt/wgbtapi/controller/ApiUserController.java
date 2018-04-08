package com.wgbt.wgbtapi.controller;

import com.wgbt.wgbtapi.domain.User;
import com.wgbt.wgbtapi.service.jwt.JwtService;
import com.wgbt.wgbtapi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        response.setHeader("Authorization", token);
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
