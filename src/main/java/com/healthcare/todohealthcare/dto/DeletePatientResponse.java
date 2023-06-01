package com.healthcare.todohealthcare.dto;

import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeletePatientResponse {
    private Long id;
    private LocalDateTime deletedDate;

    public static DeletePatientResponse toDTO (Patient patient) {
        return new DeletePatientResponse(patient.getId(), patient.getDeletedDate());
    }
}
