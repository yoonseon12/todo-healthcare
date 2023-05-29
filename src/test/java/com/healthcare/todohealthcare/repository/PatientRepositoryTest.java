package com.healthcare.todohealthcare.repository;

import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.dto.CreatePatientRequest;
import com.healthcare.todohealthcare.dto.CreatePatientResponse;
import com.healthcare.todohealthcare.dto.FindPatientResponse;
import com.healthcare.todohealthcare.service.PatientSerivice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class PatientRepositoryTest {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientSerivice patientSerivice;

    @Test
    @DisplayName("병원에 속해있는 환자를 조회한다. :: findPatientByHospitalIdAndPatientId 추상에서드 검증")
    @Transactional
    void findPatientByHospitalIdAndPatientId(){
        // given
        CreatePatientRequest patientByHospitalA =
                new CreatePatientRequest("환자A", "남", "1996-11-27", "010-1234-5678", 1L);
        CreatePatientRequest patientByHospitalB =
                new CreatePatientRequest("환자B", "남", "1999-12-12", "010-1234-5678", 2L);
        CreatePatientResponse savedPatientA = patientSerivice.create(patientByHospitalA);
        CreatePatientResponse savedPatientB = patientSerivice.create(patientByHospitalB);
        FindPatientResponse findPatientA = patientSerivice.find(savedPatientA.getId());
        FindPatientResponse findPatientB = patientSerivice.find(savedPatientB.getId());
        // when
        Optional<Patient> empty = patientRepository.findPatientByHospitalIdAndPatientId(findPatientA.getId(), findPatientB.getHospital().getId()); // 병원에 소속되지 않은 환자
        Optional<Patient> present = patientRepository.findPatientByHospitalIdAndPatientId(findPatientA.getId(), findPatientA.getHospital().getId()); // 병원에 소속된 환자
        // then
        assertTrue(empty.isEmpty());
        assertTrue(present.isPresent());
    }
}