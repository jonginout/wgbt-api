package com.wgbt.wgbtapi.answer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wgbt.wgbtapi.common.domain.AbstractEntity;
import com.wgbt.wgbtapi.question.domain.Question;
import com.wgbt.wgbtapi.user.domain.User;

import javax.persistence.*;

@Entity(name = "answers")
public class Answer extends AbstractEntity {

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


    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Answer{" + super.toString() +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                '}';
    }

}
