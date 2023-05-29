package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.entitiy.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVisitRequest {
    private String visitStateCode;
    private String departmentCode;
    private String typeCode;
    private Long hospitalId;
    private Long patientId;

    public Visit toEntity(CreateVisitRequest createVisitRequest, Hospital hospital, Patient patient) {
        return Visit.of(
                createVisitRequest.visitStateCode,
                createVisitRequest.departmentCode,
                createVisitRequest.typeCode,
                hospital,
                patient);
    }

}
