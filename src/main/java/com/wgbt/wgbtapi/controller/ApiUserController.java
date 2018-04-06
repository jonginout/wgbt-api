package com.wgbt.wgbtapi.controller;

import com.wgbt.wgbtapi.domain.User;
import com.wgbt.wgbtapi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {


    //api 서버는 private를 하지 않는다??
    @Autowired
    UserService userService;

//    @GetMapping("/{no}")
//    public User show(@PathVariable Long no){
//        return userService.detailUser(no);
//    }

    @PostMapping("")
    public Map<String, Object> show(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("doc", userService.detailUser(user.getNo()));

        return map;
    }
}
