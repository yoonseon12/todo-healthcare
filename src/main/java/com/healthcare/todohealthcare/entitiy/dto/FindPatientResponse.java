package com.healthcare.todohealthcare.entitiy.dto;

import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.entitiy.dto.entityCommonDto.HospitalResponseDTO;
import com.healthcare.todohealthcare.entitiy.dto.entityCommonDto.VisitResponseDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindPatientResponse {
    private Long id;
    private String name;
    private String registrationNo;
    private String gender;
    private String birth;
    private String phone;
    private LocalDateTime lastVisitDate;
    private HospitalResponseDTO hospital;
    private List<VisitResponseDTO> visits;

    public static FindPatientResponse toDTO(Patient patient) {
        List<VisitResponseDTO> visitResponseDTOS = patient.getVisits().stream()
                .map(p -> VisitResponseDTO.toDTO(p))
                .collect(Collectors.toList());

        return FindPatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .registrationNo(patient.getRegistrationNo())
                .gender(patient.getGender())
                .birth(patient.getBirth())
                .phone(patient.getPhone())
                .lastVisitDate(patient.getLastVisitDate())
                .hospital(HospitalResponseDTO.toDTO(patient.getHospital()))
                .visits(visitResponseDTOS)
                .build();
    }
}
