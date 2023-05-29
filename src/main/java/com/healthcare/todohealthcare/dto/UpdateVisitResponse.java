package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.entitiy.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateVisitResponse {
    private String visitStateCode;
    private String departmentCode;
    private String typeCode;
    private Long hospitalId;
    private Long patientId;

    public static UpdateVisitResponse toDTO(Visit visit) {
        return UpdateVisitResponse.builder()
                .visitStateCode(visit.getVisitStateCode())
                .departmentCode(visit.getDepartmentCode())
                .typeCode(visit.getTypeCode())
                .hospitalId(visit.getHospital().getId())
                .patientId(visit.getPatient().getId())
                .build();
    }
}
