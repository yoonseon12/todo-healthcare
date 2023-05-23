package com.healthcare.todohealthcare.entitiy.code;

import com.healthcare.todohealthcare.entitiy.audit.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "code")
public class Code extends BaseEntity {

    @EmbeddedId
    private CodeId code;

    @Column(name = "id", columnDefinition = "varchar", length = 10)
    @Comment("코드명")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codeGroup")
    @JoinColumn(name = "code_group")
    private CodeGroup codeGroup;

    public void setCodeGroup(CodeGroup codeGroup) {
        this.codeGroup = codeGroup;
    }

    public Code(CodeId code, String name) {
        this.code = code;
        this.name = name;
    }
}
