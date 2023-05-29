package com.healthcare.todohealthcare.entitiy.code;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class CodeId implements Serializable {
    @Comment("코드그룹")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_group")
    private CodeGroup codeGroup;

    @Column(name ="code", columnDefinition = "varchar", length = 10)
    @Comment("코드")
    private String code;

    public void setCodeGroup(CodeGroup codeGroup) {
        this.codeGroup = codeGroup;
    }
}
