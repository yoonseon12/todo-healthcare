package com.healthcare.todohealthcare.dto;


import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindAllPatientDTO {
    private Long id;
    private String name;
    private String registrationNo;
    private String gender;
    private String birth;
    private String phone;
    private LocalDateTime lastVisitDate;

    public static FindAllPatientDTO toDTO(Patient patient) {
        return FindAllPatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .registrationNo(patient.getRegistrationNo())
                .gender(patient.getGender())
                .birth(patient.getBirth())
                .phone(patient.getPhone())
                .lastVisitDate(patient.getLastVisitDate())
                .build();
    }
}
