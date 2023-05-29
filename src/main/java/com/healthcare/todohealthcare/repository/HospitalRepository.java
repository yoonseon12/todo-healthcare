package com.healthcare.todohealthcare.repository;

import com.healthcare.todohealthcare.entitiy.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
