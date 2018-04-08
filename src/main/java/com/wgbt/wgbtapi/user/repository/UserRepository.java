package com.wgbt.wgbtapi.user.repository;


import com.wgbt.wgbtapi.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    User findById(String id);
    List<User> findAllByOrderByNoAsc();
}