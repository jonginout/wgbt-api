package com.wgbt.wgbtapi.question.repository;


import com.wgbt.wgbtapi.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    Page<Question> findByTitleIgnoreCaseContainingOrderByCreatedDateDesc(String Title, Pageable pageable);
}