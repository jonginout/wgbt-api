package com.wgbt.wgbtapi.answer.repository;


import com.wgbt.wgbtapi.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
    List<Answer> findAllByQuestionOrderByCreatedDateDesc(Long no);
}