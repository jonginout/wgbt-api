package com.wgbt.wgbtapi.common.controller;

import com.wgbt.wgbtapi.question.domain.Question;
import com.wgbt.wgbtapi.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public String home(Model model, String query, Integer pageNo){
        if(query==null){
            query = "";
        }
        if(pageNo == null) {
            pageNo = 0;
        }
        Pageable pageable = new PageRequest(pageNo, 3, Sort.Direction.DESC, "no");
        Page<Question> page = questionRepository.findByTitleIgnoreCaseContainingOrderByCreatedDateDesc(query, pageable);

//        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        페이징에 필요는 없지만 이런 기능들도 있다는 것을 보여주기 위해 적어둡니다.
//        List<Question> questions = page.getContent();    //검색된 데이터
//        int totalPage = page.getTotalPages();      //전체 페이지 수
//        boolean hasNext = page.hasNextPage();   //다음 페이지 존재여부
//        int totalEmp = page.getTotalElements();   //검색된 Emp전체 건수
//        boolean isData = page.hasContent();      //검색된 자료가 있는가?
//        int totalData = (int) page.getTotalElements(); // 전체 데이터 수
//        int pageData = (int) page.getSize(); // 한 페이지당 데이터 수
//        int totalBlock = totalPage/blockPage; // 전체 블록 수
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        // 페이지도 블록도 모두 0이 시작
        // 개수는 그대로
        // 개수랑 현재 페이지 혹은 블록이랑 헷갈리면 안됨

        int nowPage = ((int) page.getNumber()); // 현재 페이지 ex) 0 => 1페이지
        int blockPage = 5; // 한 블록당 페이지 수
        int totalPage = (int) page.getTotalPages(); // 전체 페이지 수
        int nowBlock = (int) Math.floor((float) nowPage/blockPage); // 현재 블록 ex) 0 => 1블록

        int firstPageBlock = nowBlock*blockPage; // 현재 블록의 첫페이지
        if(firstPageBlock < 0 ){ firstPageBlock = 0; } // 시작 페이지가 1이하일 경우 1 페이지로 초기화

        int lastPageBlock = (nowBlock*blockPage) + (blockPage-1); // 블록의 끝페이지
        if( totalPage <= lastPageBlock+1 ){ lastPageBlock = totalPage-1; }; // 최대범위를 넘지 않도록 설정
        // 총 페이지 개수가 블록의 끝페이지 보다 작거나 같으면
        // 블록의 끝페이지를 총 페이지 개수(개수니깐 -1)로 초기화

        model.addAttribute("questions",page);
        model.addAttribute("firstPageBlock",firstPageBlock);
        model.addAttribute("lastPageBlock",lastPageBlock);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("nowPage",page.getNumber());
        model.addAttribute("query",query);

        return "index";
    }


}
