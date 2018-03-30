package com.wgbt.wgbtapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

//이 유저 도메인이 DB와 연결됨을 알려주는 어노테이션
@Entity(name = "users")
public class User extends AbstractEntity{

    /*
    @Id     // primary key를 지정
    @GeneratedValue     // DB에서 자동으로 1씩 증가시켜준다.
    @JsonProperty   // json으로 변환 가능하게 해준다 (getter가 없어도)
    private Long no;
    */

    @Column(nullable = false, length = 20)   // not null    // length
    @JsonProperty
    private String id;

    private String pw;

    @JsonProperty
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(User updateUser) {
        this.id = updateUser.id;
        this.pw = updateUser.pw;
        this.name = updateUser.name;
    }

    public boolean matchNo(Long newNo){
        if(newNo == null){
            return false;
        }
        return newNo == getNo();
    }
    public boolean matchPw(String newPw){
        if(newPw == null){
            return false;
        }
        return newPw.equals(pw);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
