package com.healthcare.todohealthcare.entitiy;

import com.healthcare.todohealthcare.entitiy.audit.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
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

    @Column(name = "last_visit_date", columnDefinition = "datetime")
    @Comment("최근방문일")
    private LocalDateTime lastVisitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visits = new ArrayList<>();

    @Builder
    public Patient(String name, String registrationNo, String gender, String birth, String phone) {
        this.name = name;
        this.registrationNo = registrationNo;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
    }

    public static Patient of(String name, String registrationNo,String gender, String birth, String phone) {
        return Patient.builder()
                .name(name)
                .registrationNo(registrationNo)
                .gender(gender)
                .phone(phone)
                .birth(birth)
                .build();
    }

    public Patient addPatientByhosplitalInfo(int count, Hospital hospital) {
        Patient patientInfo = createRegistrationNo(count);
        patientInfo.setHospital(hospital);

        return patientInfo;
    }

    // 환자등록번호 생성
    public Patient createRegistrationNo(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now().getYear());
        sb.append(String.format("%06d", count+1));
        setRegistrationNo(String.valueOf(sb));

        return this;
    }

    private void setRegistrationNo(String registrationNo){
        this.registrationNo = registrationNo;
    }

    private void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void isDuplicate(Patient patient) {
        if (this.name.equals(patient.name) &&
            this.birth.equals(patient.birth) &&
            this.gender.equals(patient.gender) &&
            this.phone.equals(patient.phone)) {
            throw new IllegalArgumentException("중복된 환자가 존재합니다.");
        }
    }
}
