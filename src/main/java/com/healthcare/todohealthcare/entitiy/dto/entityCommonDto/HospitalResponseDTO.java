package com.healthcare.todohealthcare.entitiy.dto.entityCommonDto;

import com.healthcare.todohealthcare.entitiy.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalResponseDTO {
    private String name;
    private String careCenterNo;
    private String director;

    public static HospitalResponseDTO toDto(Hospital hospital) {
        return HospitalResponseDTO.builder()
                .name(hospital.getName())
                .careCenterNo(hospital.getCareCenterNo())
                .director(hospital.getDirector())
                .build();
    }
}
