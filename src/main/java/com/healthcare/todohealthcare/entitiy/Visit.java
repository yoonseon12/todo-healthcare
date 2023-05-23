package com.healthcare.todohealthcare.entitiy;

import com.healthcare.todohealthcare.entitiy.audit.BaseEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Table(name ="visit")
public class Visit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    @Comment("환자방문ID")
    private Long id;

    @Column(name = "reception_date", columnDefinition = "datetime")
    @Comment("접수일시")
    private LocalDateTime receptionDate;

    @Column(name = "visit_state_code", columnDefinition = "varchar", length = 10)
    @Comment("방문상태코드")
    private String visitStateCode;

    @Column(name = "department_code", columnDefinition = "varchar", length = 10)
    @Comment("진료과목코드")
    private String departmentCode;

    @Column(name = "type_code", columnDefinition = "varchar", length = 10)
    @Comment("진료유형코드")
    private String typeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
