package com.wgbt.wgbtapi.user.service;

import com.wgbt.wgbtapi.user.domain.User;

import java.util.List;

public interface UserService {

    User loginUser(String id);
    void signinUser(User user);
    List<User> listUser();
    User detailUser(Long no);

}
