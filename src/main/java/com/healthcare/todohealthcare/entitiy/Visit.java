package com.healthcare.todohealthcare.entitiy;

import com.healthcare.todohealthcare.entitiy.audit.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

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

    @CreatedDate
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

    @Builder
    public Visit(String visitStateCode, String departmentCode, String typeCode, Hospital hospital, Patient patient) {
        this.visitStateCode = visitStateCode;
        this.departmentCode = departmentCode;
        this.typeCode = typeCode;
        this.hospital = hospital;
        this.patient = patient;
    }

    public static Visit of(String visitStateCode, String departmentCode, String typeCode, Hospital hospital, Patient patient) {
        return Visit.builder()
                .visitStateCode(visitStateCode)
                .departmentCode(departmentCode)
                .typeCode(typeCode)
                .hospital(hospital)
                .patient(patient)
                .build();
    }

    public void changeVisit(Visit visit) {
        this.visitStateCode = visit.getVisitStateCode();
        this.departmentCode = visit.getDepartmentCode();
        this.typeCode = visit.getTypeCode();
        this.hospital = visit.getHospital();
        this.patient = visit.getPatient();
    }
}
