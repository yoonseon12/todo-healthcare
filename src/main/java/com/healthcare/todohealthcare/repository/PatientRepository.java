package com.healthcare.todohealthcare.repository;

import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.repository.custom.PatientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryCustom {
    Long countByHospitalId(Long hospitalId);

    @Query("select p from Patient p join fetch p.hospital h where p.id = :patientId and h.id = :hospitalId")
    Optional<Patient> findPatientByHospitalIdAndPatientId(@Param("patientId") Long patientId, @Param("hospitalId") Long hospitalId);

    @Query("select p from Patient p join fetch p.hospital h left join fetch p.visits v where p.id = :id")
    Optional<Patient> findPatientWithHospitalWithVisitById(@Param("id") Long id);
}
