package com.healthcare.todohealthcare.repository.custom;

import com.healthcare.todohealthcare.dto.search.PatientSearchConditon;
import com.healthcare.todohealthcare.entitiy.Patient;

import java.util.List;

public interface PatientRepositoryCustom {
    List<Patient> searchPatient(PatientSearchConditon condition);
}
