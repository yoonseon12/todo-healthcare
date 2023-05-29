package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientResponse {
    private Long id;

    public static CreatePatientResponse toDTO(Patient patient) {
        return new CreatePatientResponse(patient.getId());
    }

}
