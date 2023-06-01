package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.entitiy.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteVisitResponse {
    private Long id;
    private LocalDateTime deletedDate;

    public static DeleteVisitResponse toDTO (Visit visit) {
        return new DeleteVisitResponse(visit.getId(), visit.getDeletedDate());
    }
}
