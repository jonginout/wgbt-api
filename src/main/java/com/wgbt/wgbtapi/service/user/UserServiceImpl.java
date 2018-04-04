package com.wgbt.wgbtapi.service.user;

import com.wgbt.wgbtapi.domain.User;
import com.wgbt.wgbtapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loginUser(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void signinUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAllByOrderByNoAsc();
    }

    @Override
    public User detailUser(Long no) {
        return userRepository.findOne(no);
    }
}
