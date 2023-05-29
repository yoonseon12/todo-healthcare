package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePatientRequest {
    private String name;
    private String gender;
    private String birth;
    private String phone;
    private Long hospitalId;

    public Patient toEntity(UpdatePatientRequest updatePatientRequest, Hospital hospital) {
        return Patient.of(updatePatientRequest.name,
                null,
                updatePatientRequest.gender,
                updatePatientRequest.birth,
                updatePatientRequest.phone,
                hospital);
    }
}
