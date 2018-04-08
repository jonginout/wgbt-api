package com.wgbt.wgbtapi.question.service;

import com.wgbt.wgbtapi.question.domain.Question;
import com.wgbt.wgbtapi.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void postQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question detailQuestion(Long no) {
        return questionRepository.findOne(no);
    }

    @Override
    public void deleteQuestion(Long no) {
        questionRepository.delete(no);
    }
}
