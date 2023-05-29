package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePatientResponse {
    private String name;
    private String gender;
    private String birth;
    private String phone;
    private Long hospitalId;

    public static UpdatePatientResponse toDTO(Patient patient) {
        return UpdatePatientResponse.builder()
                .name(patient.getName())
                .gender(patient.getGender())
                .birth(patient.getBirth())
                .phone(patient.getPhone())
                .hospitalId(patient.getHospital().getId())
                .build();
    }
}
