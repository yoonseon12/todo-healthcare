package com.healthcare.todohealthcare.dto.entityCommonDto;

import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDTO {
    private String name;
    private String gender;
    private String birth;
    private String phone;

    public static PatientResponseDTO toDTO(Patient patient) {
        return PatientResponseDTO.builder()
                .name(patient.getName())
                .gender(patient.getGender())
                .birth(patient.getBirth())
                .phone(patient.getPhone())
                .build();
    }
}
