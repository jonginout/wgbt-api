package com.wgbt.wgbtapi.service.question;

import com.wgbt.wgbtapi.domain.Question;

public interface QuestionService {

    void postQuestion(Question question);
    Question detailQuestion(Long no);
    void deleteQuestion(Long no);

}
