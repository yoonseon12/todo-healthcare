package com.healthcare.todohealthcare.entitiy.code;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class CodeId implements Serializable {
    @Comment("코드그룹")
    private String codeGroup;

    @Column(name ="code", columnDefinition = "varchar", length = 10)
    @Comment("코드")
    private String code;
}
