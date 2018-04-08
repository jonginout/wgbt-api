package com.wgbt.wgbtapi.question.service;

import com.wgbt.wgbtapi.question.domain.Question;

public interface QuestionService {

    void postQuestion(Question question);
    Question detailQuestion(Long no);
    void deleteQuestion(Long no);

}
