package com.board.service.user;

import com.board.repository.domain.User;
import com.board.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService{

    //스프링이 알아서 의존 객체를 찾아서 주입
    @Autowired
    private UserMapper mapper;

    @Override
    public User loginUser(String id) throws Exception {
        return mapper.getUser(id);
    }
}
