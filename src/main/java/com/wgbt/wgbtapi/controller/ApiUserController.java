package com.wgbt.wgbtapi.controller;

import com.wgbt.wgbtapi.domain.User;
import com.wgbt.wgbtapi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {


    //api 서버는 private를 하지 않는다??
    @Autowired
    UserService userService;

    @GetMapping("/{no}")
    public User show(@PathVariable Long no){
        return userService.detailUser(no);
    }

}
