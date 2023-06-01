package com.healthcare.todohealthcare.repository;

import com.healthcare.todohealthcare.entitiy.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("select v from Visit v join fetch v.hospital join fetch v.patient where v.id= :id")
    Optional<Visit> findVisitWithHospitalWithPatientById(@Param("id") Long id);

    @Query(value = "SELECT V.* FROM VISIT V WHERE ID = :id", nativeQuery = true)
    Optional<Visit> findDeletedVisitById(@Param("id") Long id);
}
