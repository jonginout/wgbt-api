package com.wgbt.wgbtapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity(name = "answers")
public class Answer extends AbstractEntity{

    // q는 a에 원투매니
    // a는 q에 매니투원

    /*
    @Id
    @GeneratedValue
    @JsonProperty
    private Long no;
    */

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    @JsonProperty
    private User writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    @JsonProperty
    private Question question;

    @JsonProperty
    private String content;

    public Answer() {
    }

    public Answer(User writer, Question question, String content) {
        this.writer = writer;
        this.question = question;
        this.content = content;
    }

    public boolean matchWriter(User loginUser) {
        return loginUser.equals(this.writer);
    }

    @Override
    public String toString() {
        return "Answer{" + super.toString() +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                '}';
    }

}
