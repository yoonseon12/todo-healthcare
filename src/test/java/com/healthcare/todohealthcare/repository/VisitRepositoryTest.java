package com.healthcare.todohealthcare.repository;

import com.healthcare.todohealthcare.dto.CreatePatientRequest;
import com.healthcare.todohealthcare.dto.CreatePatientResponse;
import com.healthcare.todohealthcare.dto.CreateVisitRequest;
import com.healthcare.todohealthcare.dto.FindPatientResponse;
import com.healthcare.todohealthcare.entitiy.Visit;
import com.healthcare.todohealthcare.service.PatientSerivice;
import com.healthcare.todohealthcare.service.VisitService;
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
    private VisitService visitService;
    @Autowired
    private PatientSerivice patientSerivice;

    @Test
    @DisplayName("식별자의 방문 정보를 조회한다. :: findVisitWithHospitalWithPatientById 추상메서드 검증")
    @Transactional
    public void findVisitWithHospitalWithPatientById() {
        // given
        CreatePatientRequest patientByHospitalA =
                new CreatePatientRequest("환자A","남","1996-11-27","010-1234-5678",1L);
        CreatePatientRequest patientByHospitalB =
                new CreatePatientRequest("환자B","남","1999-12-12","010-1234-5678",2L);
        CreatePatientResponse savedPatientA = patientSerivice.create(patientByHospitalA);
        CreatePatientResponse savedPatientB = patientSerivice.create(patientByHospitalB);
        FindPatientResponse findPatientA = patientSerivice.find(savedPatientA.getId());
        FindPatientResponse findPatientB = patientSerivice.find(savedPatientB.getId());

        CreateVisitRequest visitByPatient = new CreateVisitRequest("1", "01", "D", 1L, savedPatientA.getId());
        visitService.create(visitByPatient);
        // when
        Optional<Visit> result = visitRepository.findVisitWithHospitalWithPatientById(savedPatientA.getId());
        // then
        assertTrue(result.isPresent());
    }

}