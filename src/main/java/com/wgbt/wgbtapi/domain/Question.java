package com.wgbt.wgbtapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity(name = "questions")
public class Question extends AbstractEntity{

    /*
    @Id     // primary key를 지정
    @GeneratedValue     // DB에서 자동으로 1씩 증가시켜준다.
    @JsonProperty
    private Long no;
    */

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    @JsonProperty
    private User writer;
    // 포인 키 설정 // User의 PK가 들어가도록 설정 ( 즉여기서는 User의 no )
    // 그리고 실제 table에는 writer_no 이런식으로 사용
    // writer에 user 정보를
    // question과 user 관계는 manyToOne의 관계다

    @JsonProperty
    private String title;

    /*
    @Column(name = "reg_date")
//    @JsonProperty // 밑에 get getFormattedregDate 있어서 ㄱㅊ
    private LocalDateTime regDate;
    */

    // 긴 컨텐츠 .... longblob
    @Lob
    @JsonProperty
    private String content;

    @OneToMany(mappedBy = "question") // mappedBy Answer의 매니투원의 필드 이름을 적으면 된다.
    @OrderBy("no ASC") // answer의 no로 오름차순
    private List<Answer> answers;

    @Column(name = "count_of_answer")
    @JsonProperty
    private Integer countOfAnswer = 0;

    // 기본 생성자는 필수
    public Question() {
    }

    public Question(User writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public boolean matchUser(User loginUser) {
        return this.writer.equals(loginUser);
        // user에 equals 오버라이드
    }

    public void addAnswer() {
        this.countOfAnswer += 1;
    }
    public void deleteAnswer() {
        this.countOfAnswer -= 1;
    }

    @Override
    public String toString() {
        return "Question{" + super.toString() +
                ", writer=" + writer +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", answers=" + answers +
                ", countOfAnswer=" + countOfAnswer +
                '}';
    }
}
