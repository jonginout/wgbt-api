package com.wgbt.wgbtapi.service.answer;

import com.wgbt.wgbtapi.domain.Answer;

public interface AnswerService {

    Answer postAnswer(Answer answer);
    Answer detailAnswer(Long no);
    void deleteAnswer(Long no);

}
