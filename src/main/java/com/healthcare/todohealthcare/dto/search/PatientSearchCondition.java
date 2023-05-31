package com.healthcare.todohealthcare.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientSearchCondition {
    private String name;
    private String registrationNo;
    private String birth;
}
