package com.healthcare.todohealthcare.dto.entityCommonDto;

import com.healthcare.todohealthcare.entitiy.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitResponseDTO {
    private String visitStateCode;
    private String departmentCode;
    private String typeCode;
    private LocalDateTime receptionDate;
    public static VisitResponseDTO toDTO(Visit visit) {
        return VisitResponseDTO.builder()
                .visitStateCode(visit.getVisitStateCode())
                .departmentCode(visit.getDepartmentCode())
                .typeCode(visit.getTypeCode())
                .receptionDate(visit.getReceptionDate())
                .build();
    }
}
