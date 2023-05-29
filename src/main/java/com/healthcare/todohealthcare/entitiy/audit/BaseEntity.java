package com.healthcare.todohealthcare.entitiy.audit;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    @Comment("생성일자")
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Comment("수정일자")
    private LocalDateTime lastModifiedDate;

    @Column(name = "deleted_date",columnDefinition = "DATETIME", updatable = false)
    @Comment("삭제일자")
    private LocalDateTime deletedDate;
}
