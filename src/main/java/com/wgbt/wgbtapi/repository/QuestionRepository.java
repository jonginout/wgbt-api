package com.wgbt.wgbtapi.repository;


import com.wgbt.wgbtapi.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    Page<Question> findByTitleIgnoreCaseContainingOrderByCreatedDateDesc(String Title, Pageable pageable);
}