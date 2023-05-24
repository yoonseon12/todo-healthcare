package com.healthcare.todohealthcare.entitiy.dto;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientRequest {
    private String name;
    private String gender;
    private String birth;
    private String phone;
    private Long hospitalId;
    public Patient toEntity(CreatePatientRequest createPatientRequest, Hospital hospital) {
        return Patient.of(createPatientRequest.name,
                null,
                createPatientRequest.gender,
                createPatientRequest.birth,
                createPatientRequest.phone,
                hospital);
    }
}
