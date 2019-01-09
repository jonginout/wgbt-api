package com.wgbt.wgbtapi.common.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// 부모클래스의 어노테이션
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // auditing 리스닝
public class AbstractEntity {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long no;

//    설정파일에
//    @EnableJpaAuditing    // 데이터 변경사항 추적 Auditing 데이터가 생성될때 Auditing 해준다.
//    필수

    @CreatedDate    //자동으로 데이터 추가
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    public Long getNo() {
        return no;
    }

    public String getFormattedCreatedDate(){
        return getFormattedDate(createdDate, "yy년 MM월 dd일 HH:mm:ss");
    }
    public String getFormattedModifiedDate(){
        return getFormattedDate(modifiedDate, "yy년 MM월 dd일 HH:mm:ss");
    }
    private String getFormattedDate(LocalDateTime dateTime, String format){
        if(dateTime == null){
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(no, that.no);
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "no=" + no +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
