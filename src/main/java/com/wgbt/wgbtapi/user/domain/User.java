package com.wgbt.wgbtapi.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wgbt.wgbtapi.common.domain.AbstractEntity;
import lombok.Setter;

import javax.persistence.*;

// Entity : 이 유저 도메인이 DB와 연결됨을 알려주는 어노테이션
// JsonIgnoreProperties(ignoreUnknown = true) :
// json 데이터 중 domain에 없는 컬럼이 있을 경우 원래는 에러를 발생시키는데 그런 컬럼은 무시하도록 하게 하는 것.
@Entity(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends AbstractEntity {

    /*
    @Id     // primary key를 지정
    @GeneratedValue     // DB에서 자동으로 1씩 증가시켜준다.
    @JsonProperty   // json으로 변환 가능하게 해준다 (getter가 없어도)
    private Long no;
    */

    @Column(nullable = false, length = 20, unique=true)   // not null    // length
    @JsonProperty   //json으로 반환될때 처리하고 싶은것만 어노테이션 한다 (로 getter setter 이름을 바꿀 수 있다.)
    private String id;

    private String pw;

    @JsonProperty
    private String name;

    @Transient
    private String save;

    public String getId() {
        return id;
    }
    public String getPw() {
        return pw;
    }
    public String getSave() {
        return save;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSave(String save) {
        this.save = save;
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
