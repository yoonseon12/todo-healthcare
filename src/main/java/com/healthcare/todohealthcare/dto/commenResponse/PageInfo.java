package com.healthcare.todohealthcare.dto.commenResponse;

import com.healthcare.todohealthcare.entitiy.Patient;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PageInfo {
    private int pageNo;
    private int pageSize;
    private Long totalCount;

    public static PageInfo toDTO(Page<Patient> patients) {
        return PageInfo.builder()
                .pageNo(patients.getNumber()+1)
                .pageSize(patients.getSize())
                .totalCount(patients.getTotalElements())
                .build();
    }
}
