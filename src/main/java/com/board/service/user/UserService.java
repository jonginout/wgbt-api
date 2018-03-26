package com.board.service.user;

import com.board.repository.domain.User;

public interface UserService {

    public User loginUser(String id) throws Exception;

}
