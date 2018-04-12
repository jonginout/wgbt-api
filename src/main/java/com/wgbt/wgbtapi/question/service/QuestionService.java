package com.wgbt.wgbtapi.question.service;

import com.wgbt.wgbtapi.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

    void postQuestion(Question question);
    Question detailQuestion(Long no);
    void deleteQuestion(Long no);

    Page<Object> listQuestionbyTitle(String query, Pageable pageable);

}
