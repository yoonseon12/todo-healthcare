package com.healthcare.todohealthcare.entitiy.dto;

import com.healthcare.todohealthcare.entitiy.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVisitResponse {
    private Long id;

    public static CreateVisitResponse toDTO(Visit visit) {
        return new CreateVisitResponse(visit.getId());
    }
}
