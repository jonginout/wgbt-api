package com.wgbt.wgbtapi.web;

import com.wgbt.wgbtapi.domain.User;
import com.wgbt.wgbtapi.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{no}")
    public User show(@PathVariable Long no){
        return userRepository.findOne(no);
    }

}
