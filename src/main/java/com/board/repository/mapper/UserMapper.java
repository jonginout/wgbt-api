package com.board.repository.mapper;

import com.board.repository.domain.User;

public interface UserMapper {

    public User getUser(String id) throws Exception;

}
