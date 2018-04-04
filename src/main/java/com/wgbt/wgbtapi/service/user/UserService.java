package com.wgbt.wgbtapi.service.user;

import com.wgbt.wgbtapi.domain.User;

import java.util.List;

public interface UserService {

    User loginUser(String id);
    void signinUser(User user);
    List<User> listUser();
    User detailUser(Long no);

}
