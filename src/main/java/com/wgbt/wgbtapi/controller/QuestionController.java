package com.wgbt.wgbtapi.controller;

import com.wgbt.wgbtapi.common.HttpSessionUtills;
import com.wgbt.wgbtapi.domain.Question;
import com.wgbt.wgbtapi.domain.Result;
import com.wgbt.wgbtapi.domain.User;
import com.wgbt.wgbtapi.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    private Result isWriter(HttpSession session, Question question){
        if(!HttpSessionUtills.isLoginUser(session)){
            return Result.fail("로그인이 필요합니다");
        }
        User loginUser = HttpSessionUtills.getUserFromSession(session);
        if(!question.matchUser(loginUser)){
            return Result.fail("타인의 글은 처리 불가능 합니다.");
        }
        return Result.ok();
    }

    private Result isLogin(HttpSession session){
        if(!HttpSessionUtills.isLoginUser(session)){
            return Result.fail("로그인이 필요합니다");
        }
        return Result.ok();
    }

    @GetMapping("/form")
    public String form(HttpSession session, Model model){

        Result result = isLogin(session);
        if(!result.isSuccess()){
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/login";
        }
        return "/qna/form";

    }

    @PostMapping("")
    public String write(String title, String content, HttpSession session, Model model){

        Result result = isLogin(session);
        if(!result.isSuccess()){
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/login";
        }

        User loginUser = HttpSessionUtills.getUserFromSession(session);
        Question newQuestion = new Question(loginUser, title, content);
        questionService.postQuestion(newQuestion);
        return "redirect:/";

    }

    @GetMapping("/{no}")
    public String detail(@PathVariable Long no, HttpSession session, Model model){

        Result result = isLogin(session);
        if(!result.isSuccess()){
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/login";
        }

        model.addAttribute("question", questionService.detailQuestion(no));
        //answerRepository.findByQuestionNo() // 이 방법은 사용 XX // 이때 원투매니 관계를 사용
        return "/qna/detail";

    }


    @GetMapping("/{no}/form")
    public String updateForm(@PathVariable Long no, HttpSession session, Model model){

        Question question = questionService.detailQuestion(no);
        Result result = isWriter(session, question);
        if(!result.isSuccess()){
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/login";
        }
        model.addAttribute("question", question);
        return "/qna/updateForm";

    }

    @PutMapping("/{no}")
    public String update(
            @PathVariable Long no, String title,
            String content, HttpSession session, Model model){

        Question question = questionService.detailQuestion(no);
        Result result = isWriter(session, question);
        if(!result.isSuccess()){
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/login";
        }
        question.update(title, content);
        questionService.postQuestion(question);
        return "redirect:/question/"+no;

    }


    @DeleteMapping("/{no}")
    public String delete( @PathVariable Long no, HttpSession session, Model model){

        Question question = questionService.detailQuestion(no);
        Result result = isWriter(session, question);
        if(!result.isSuccess()){
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/login";
        }
        questionService.deleteQuestion(no);
        return "redirect:/question/"+no;

    }

    

}
