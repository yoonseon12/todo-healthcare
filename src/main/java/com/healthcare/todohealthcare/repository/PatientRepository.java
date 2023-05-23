package com.healthcare.todohealthcare.repository;

import com.healthcare.todohealthcare.entitiy.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
