package com.wgbt.wgbtapi.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

public class Pagination {
        // 페이지도 블록도 모두 0이 시작
        // 개수는 그대로
        // 개수랑 현재 페이지 혹은 블록이랑 헷갈리면 안됨


        // 한 블록당 페이지 수
        private int blockPage = 5;

        @JsonProperty
        private Page<Object> list;
        @JsonProperty
        private int firstPageBlock;
        @JsonProperty
        private int lastPageBlock;
        @JsonProperty
        private int totalPage;
        @JsonProperty
        private int nowPage;


        public Pagination() {}
        public Pagination(Page<Object> page) {
                list = page;

                totalPage = (int) page.getTotalPages();         // 전체 페이지
                nowPage = ((int) page.getNumber());     // 현재 페이지
                if(nowPage > totalPage){ nowPage = 0; }


                int nowBlock = (int) Math.floor((float) nowPage/blockPage);     // 현재 블록

                firstPageBlock = nowBlock*blockPage;    // 블록의 첫페이지
                if(firstPageBlock < 0 ){ firstPageBlock = 0; }
                // 시작 페이지가 1이하일 경우 1 페이지로 초기화

                lastPageBlock = (nowBlock*blockPage) + (blockPage-1);   // 블록의 끝페이지
                if( totalPage <= lastPageBlock+1 ){ lastPageBlock = totalPage-1; };
                // 총 페이지 개수가 블록의 끝페이지 보다 작거나 같으면
                // 블록의 끝페이지를 총 페이지 개수(개수니깐 -1)로 초기화
        }

}





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