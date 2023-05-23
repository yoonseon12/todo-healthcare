package com.healthcare.todohealthcare.entitiy.code;

import com.healthcare.todohealthcare.entitiy.audit.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "codeGroup")
public class CodeGroup extends BaseEntity {

    @Id
    @Column(name = "code_group", columnDefinition = "varchar", length = 10)
    @Comment("코드그룹")
    private String codeGroup;

    @Column(name = "name", columnDefinition = "varchar", length = 10)
    @Comment("코드그룹명")
    private String name;

    @Column(name = "explanation", columnDefinition = "varchar", length = 10)
    @Comment("설명")
    private String explanation;

}
