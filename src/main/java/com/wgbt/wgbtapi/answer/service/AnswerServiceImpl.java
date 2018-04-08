package com.wgbt.wgbtapi.answer.service;

import com.wgbt.wgbtapi.answer.domain.Answer;
import com.wgbt.wgbtapi.answer.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer postAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer detailAnswer(Long no) {
        return answerRepository.findOne(no);
    }

    @Override
    public void deleteAnswer(Long no) {
        answerRepository.delete(no);
    }
}
