package com.wgbt.wgbtapi.repository;


import com.wgbt.wgbtapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    User findById(String id);
    List<User> findAllByOrderByNoAsc();
}