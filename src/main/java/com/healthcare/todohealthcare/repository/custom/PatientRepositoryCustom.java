package com.healthcare.todohealthcare.repository.custom;

import com.healthcare.todohealthcare.dto.search.PatientSearchCondition;
import com.healthcare.todohealthcare.entitiy.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientRepositoryCustom {
    Page<Patient> searchPatient(PatientSearchCondition condition, Pageable pageable);
}
