package com.healthcare.todohealthcare.repository;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.entitiy.Visit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
@Transactional(readOnly = true)
class VisitRepositoryTest {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("식별자의 방문 정보를 조회한다. :: findVisitWithHospitalWithPatientById 추상메서드 검증")
    @Transactional
    public void findVisitWithHospitalWithPatientById() {
        // given
        Hospital hospital = new Hospital("G병원","90","G병원장");
        hospitalRepository.save(hospital);
        Patient patient = new Patient("환자A",null, "M", "1990-01-01", "010-1234-5678", hospital);
        Patient savedPatient = patientRepository.save(patient);
        Visit visit = new Visit("1", null, "D", hospital, patient);
        visitRepository.save(visit);
        // when
        Optional<Visit> result = visitRepository.findVisitWithHospitalWithPatientById(savedPatient.getId());
        // then
        assertTrue(result.isPresent());
    }

}