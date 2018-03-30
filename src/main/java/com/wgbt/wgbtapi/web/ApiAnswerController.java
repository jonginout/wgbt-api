package com.wgbt.wgbtapi.web;

import com.wgbt.wgbtapi.HttpSessionUtills;
import com.wgbt.wgbtapi.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

// 답변은 question에 항상 종속적이기 때문에

@RestController //json
@RequestMapping("/api/question/{questionNo}/answer")
public class ApiAnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;


    @PostMapping("")
    public Answer create(@PathVariable Long questionNo, String content, HttpSession session){
        if(!HttpSessionUtills.isLoginUser(session)){
            return null;
        }
        User loginUser = HttpSessionUtills.getUserFromSession(session);
        Question question = questionRepository.findOne(questionNo);
        Answer answer = new Answer(loginUser, question, content);
        question.addAnswer();
        return answerRepository.save(answer);   // save를 했지만 answer 타입이다.
    }


    @DeleteMapping("/{no}")
    public Map<String, Object> delete(
            @PathVariable Long questionNo, @PathVariable Long no,
            HttpSession session){
        Map<String, Object> map = new HashMap<>();
        // map은 꼭 메소드 안에서 선언;;... 해시맵으로

        if(!HttpSessionUtills.isLoginUser(session)){
            map.put("success", false);
            map.put("msg", "로그인 필요");
            return map;
        }

        Answer answer = answerRepository.findOne(no);
        User loginUser = HttpSessionUtills.getUserFromSession(session);
        if(!answer.matchWriter(loginUser)){
            map.put("success", false);
            map.put("msg", "타인 댓글 처리 불가.");
            return map;
        }

        answerRepository.delete(no);
        map.put("success", true);
        Question question = questionRepository.findOne(questionNo);
        question.deleteAnswer();
        questionRepository.save(question);
        return map;
    }

}
