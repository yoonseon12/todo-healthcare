package com.healthcare.todohealthcare.entitiy;

import com.healthcare.todohealthcare.entitiy.audit.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "patient")
public class Patient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    @Comment("환자ID")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar", length = 45)
    @Comment("환자명")
    private String name;

    @Column(name = "registration_no", columnDefinition = "varchar", length = 13)
    @Comment("환자등록번호")
    private String registrationNo;

    @Column(name = "gender", columnDefinition = "varchar", length = 10)
    @Comment("성별")
    private String gender;

    @Column(name = "birth", columnDefinition = "varchar", length = 10)
    @Comment("생년월일")
    private String birth;

    @Column(name = "phone", columnDefinition = "varchar", length = 20)
    @Comment("휴대전화번호")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visits = new ArrayList<>();

}
