package com.wgbt.wgbtapi.web;

import com.wgbt.wgbtapi.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("")
    public String home(Model model, String query){
        if(query==null){
            query = "";
        }
        model.addAttribute(
                "questions",
                questionRepository.findByTitleIgnoreCaseContainingOrderByCreatedDateDesc(query)
        );
        return "index";
    }

}
