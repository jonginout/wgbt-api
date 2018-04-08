package com.wgbt.wgbtapi.user.controller;


import com.wgbt.wgbtapi.common.utill.HttpSessionUtills;
import com.wgbt.wgbtapi.user.domain.User;
import com.wgbt.wgbtapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String loginForm(){
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(String id, String pw, HttpSession session){
        User loginUser = userService.loginUser(id);

        if(loginUser == null || !loginUser.matchPw(pw)){
            System.out.println("로그인 실패");
            return "redirect:/user/login";
        }
        session.setAttribute(HttpSessionUtills.USER_SESSION_KEY, loginUser);
        System.out.println("로그인 성공v");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(HttpSessionUtills.USER_SESSION_KEY);
        return "redirect:/";
    }

    @GetMapping("/form")
    public String form(){
        return "/user/form";
    }

    @PostMapping("")
    public String create(User user){
        userService.signinUser(user);
        return "redirect:/user"; // list로 바로 리다이렉트
    }

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("users", userService.listUser());
        return "/user/list"; // template 파일 호출
    }

    @GetMapping("{no}/form") // {id} 값을 가져오기 위해
    public String updateForm(@PathVariable Long no, Model model, HttpSession session) throws Exception {

        if(!HttpSessionUtills.isLoginUser(session)){
            return "redirect:/user/login";
        }
        User me = HttpSessionUtills.getUserFromSession(session);
        if(!me.matchNo(no)){
            throw new IllegalAccessException("You can't update the another user!!");
        }

        model.addAttribute("user", userService.detailUser(no));
        return "/user/updateForm";
    }

    @PutMapping("/{no}")
    public String update (@PathVariable Long no, User updateUser, HttpSession session) throws Exception {

        if(!HttpSessionUtills.isLoginUser(session)){
            return "redirect:/user/login";
        }
        User me = HttpSessionUtills.getUserFromSession(session);
        if(!me.matchNo(no)){
            throw new IllegalAccessException("You can't update the another user!!");
        }

        User user = userService.detailUser(no);
        user.update(updateUser);
        userService.signinUser(user);
        return "redirect:/user";
    }


}
