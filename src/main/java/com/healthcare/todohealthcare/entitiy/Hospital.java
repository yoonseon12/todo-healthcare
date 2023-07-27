package com.healthcare.todohealthcare.entitiy;

import com.healthcare.todohealthcare.entitiy.audit.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Table(name = "hospital")
public class Hospital extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    @Comment("병원ID")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar", length = 45)
    @Comment("병원명")
    private String name;

    @Column(name = "care_center_no", columnDefinition = "varchar", length = 20)
    @Comment("요양기관번호")
    private String careCenterNo;

    @Column(name ="director", columnDefinition = "varchar", length = 10)
    @Comment("병원장")
    private String director;

    @OneToMany(mappedBy = "hospital")
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "hospital")
    private List<Visit> visits = new ArrayList<>();

    @Builder
    public Hospital(Long id, String name, String careCenterNo, String director) {
        this.id = id;
        this.name = name;
        this.careCenterNo = careCenterNo;
        this.director = director;
    }
}
