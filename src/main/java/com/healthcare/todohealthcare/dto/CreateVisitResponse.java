package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.entitiy.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVisitResponse {
    private Long id;
    private LocalDateTime createdDate;

    public static CreateVisitResponse toDTO(Visit visit) {
        return new CreateVisitResponse(visit.getId(), visit.getCreatedDate());
    }
}
