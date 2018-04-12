package com.wgbt.wgbtapi.question.controller;

import com.wgbt.wgbtapi.common.domain.Pagination;
import com.wgbt.wgbtapi.question.domain.Question;
import com.wgbt.wgbtapi.question.repository.QuestionRepository;
import com.wgbt.wgbtapi.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
public class ApiQuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public Map<String, Object> list(String query, Integer nowPage){
        if(query==null){ query = ""; }
        if(nowPage == null) { nowPage = 0; }

        Pageable pageable;
        Page<Object> page;
        checkOverList:while (true){
            pageable = new PageRequest(nowPage, 3, Sort.Direction.DESC, "no");  // no를 기준으로 내림차순
            page = questionService.listQuestionbyTitle(query, pageable);

            if( (int) page.getTotalPages() > 0 && (int) page.getNumber()+1 > (int) page.getTotalPages()){
                nowPage = 0;
                continue checkOverList;
            }
            break;
        }

        Map<String, Object> map = new HashMap<>();
        Pagination pageResult = new Pagination(page);

        map.put("success", true);
        map.put("page", pageResult);

        return map;
    }


}
