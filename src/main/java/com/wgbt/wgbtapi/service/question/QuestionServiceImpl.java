package com.wgbt.wgbtapi.service.question;

import com.wgbt.wgbtapi.domain.Question;
import com.wgbt.wgbtapi.repository.QuestionRepository;
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
