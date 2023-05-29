package com.healthcare.todohealthcare.dto;


import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindAllPatientResponse {
    private List<FindAllPatientDTO> patients;

    public static FindAllPatientResponse toDTO(List<Patient> patients) {
        List<FindAllPatientDTO> list = patients.stream()
                .map(p -> FindAllPatientDTO.toDTO(p)).
                collect(Collectors.toList());

        return FindAllPatientResponse.builder()
                .patients(list)
                .build();
    }
}
