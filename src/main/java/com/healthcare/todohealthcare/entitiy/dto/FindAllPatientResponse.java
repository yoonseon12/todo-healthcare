package com.healthcare.todohealthcare.entitiy.dto;


import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindAllPatientResponse {
    private Long id;
    private String name;
    private String registrationNo;
    private String gender;
    private String birth;
    private String phone;
    private LocalDateTime lastVisitDate;

    public static FindAllPatientResponse toDTO(Patient patient) {
        return FindAllPatientResponse.builder()
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
