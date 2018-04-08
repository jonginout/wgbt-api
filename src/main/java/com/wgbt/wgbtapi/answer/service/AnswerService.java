package com.wgbt.wgbtapi.answer.service;

import com.wgbt.wgbtapi.answer.domain.Answer;

public interface AnswerService {

    Answer postAnswer(Answer answer);
    Answer detailAnswer(Long no);
    void deleteAnswer(Long no);

}
