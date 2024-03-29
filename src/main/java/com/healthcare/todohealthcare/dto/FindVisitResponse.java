package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.dto.entityCommonDto.HospitalResponseDTO;
import com.healthcare.todohealthcare.entitiy.Visit;
import com.healthcare.todohealthcare.dto.entityCommonDto.PatientResponseDTO;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindVisitResponse {
    private String visitStateCode;
    private String departmentCode;
    private String typeCode;
    private HospitalResponseDTO hospital;
    private PatientResponseDTO patient;

    public static FindVisitResponse toDTO(Visit visit) {
        return FindVisitResponse.builder()
                .visitStateCode(visit.getVisitStateCode())
                .departmentCode(visit.getDepartmentCode())
                .typeCode(visit.getTypeCode())
                .hospital(HospitalResponseDTO.toDTO(visit.getHospital()))
                .patient(PatientResponseDTO.toDTO(visit.getPatient()))
                .build();
    }
}
