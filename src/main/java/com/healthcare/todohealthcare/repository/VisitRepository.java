package com.healthcare.todohealthcare.repository;

import com.healthcare.todohealthcare.entitiy.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT v FROM Visit v JOIN FETCH v.hospital JOIN FETCH v.patient WHERE v.id= :id")
    Optional<Visit> findVisitWithHospitalAndPatient(@Param("id") Long id);
}
