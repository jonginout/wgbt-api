package com.wgbt.wgbtapi.controller;

import com.wgbt.wgbtapi.common.HttpSessionUtills;
import com.wgbt.wgbtapi.domain.*;
import com.wgbt.wgbtapi.service.answer.AnswerService;
import com.wgbt.wgbtapi.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

// 답변은 question에 항상 종속적이기 때문에

@RestController //json
@RequestMapping("/api/question/{questionNo}/answer")
public class ApiAnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;


    @PostMapping("")
    public Answer create(@PathVariable Long questionNo, String content, HttpSession session){
        if(!HttpSessionUtills.isLoginUser(session)){
            return null;
        }
        User loginUser = HttpSessionUtills.getUserFromSession(session);
        Question question = questionService.detailQuestion(questionNo);
        Answer answer = new Answer(loginUser, question, content);
        question.addAnswer();
        return answerService.postAnswer(answer); // save를 했지만 answer 타입이다.
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

        Answer answer = answerService.detailAnswer(no);
        User loginUser = HttpSessionUtills.getUserFromSession(session);
        if(!answer.matchWriter(loginUser)){
            map.put("success", false);
            map.put("msg", "타인 댓글 처리 불가.");
            return map;
        }

        answerService.deleteAnswer(no);
        map.put("success", true);
        Question question = questionService.detailQuestion(questionNo);
        question.deleteAnswer();
        questionService.postQuestion(question);
        return map;
    }

}
