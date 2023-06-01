package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.dto.*;
import com.healthcare.todohealthcare.entitiy.Visit;
import com.healthcare.todohealthcare.repository.PatientRepository;
import com.healthcare.todohealthcare.repository.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional(readOnly = true)
class VisitServiceTest {
    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private PatientSerivice patientSerivice;

    @Test
    @DisplayName("방문정보를 생성한다.")
    @Transactional
    void createVisit() {
        // given
        CreatePatientRequest createPatientERequest =
                new CreatePatientRequest("환자E","M","1996-11-27","010-1234-5678",1L);
        CreatePatientResponse savedPatientE = patientSerivice.create(createPatientERequest);
        CreatePatientRequest createPatientBRequest =
                new CreatePatientRequest("환자R","M","1996-11-27","010-1234-5678",2L);
        CreatePatientResponse savedPatientR = patientSerivice.create(createPatientBRequest);
        CreateVisitRequest visitByPatientA = new CreateVisitRequest("1", "01", "D", 1L, savedPatientE.getId());
        CreateVisitRequest visitByPatientB = new CreateVisitRequest("1", "02", "T", 2L, savedPatientR.getId());
        // when

        // then
        assertThatCode(() -> visitService.create(visitByPatientA)).doesNotThrowAnyException();
        assertThatCode(() -> visitService.create(visitByPatientB)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("방문정보 생성 시 병원에 속해있는 환자를 검증한다.")
    void createVisitValidate() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자K","M","1996-11-27","010-1234-5678",1L);
        CreatePatientResponse savedPatient = patientSerivice.create(createPatientRequest);
        CreateVisitRequest visitByPatient = new CreateVisitRequest("1", "01", "D", 2L, savedPatient.getId());
        // when

        // then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> visitService.create(visitByPatient));
        assertThat(exception.getMessage()).isEqualTo("해당 병원에 등록된 환자가 없습니다.");
    }


    @Test
    @DisplayName("방문정보를 수정한다.")
    @Transactional
    void updateVisit() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자T","M","1996-11-27","010-1234-5678",1L);
        CreatePatientResponse savedPatient = patientSerivice.create(createPatientRequest);
        CreateVisitRequest visitByPatient = new CreateVisitRequest("1", "01", "D", 1L, savedPatient.getId());
        CreateVisitResponse savedVisit = visitService.create(visitByPatient);

        UpdateVisitRequest updateVisitRequest = new UpdateVisitRequest("2", "02", "T", 1L, savedPatient.getId());
        // when
        UpdateVisitResponse updatedVisit = visitService.update(savedVisit.getId(), updateVisitRequest);
        // then
        assertThat(visitRepository.findById(savedVisit.getId()).get().getVisitStateCode())
                .isEqualTo(updatedVisit.getVisitStateCode());
        assertThat(visitRepository.findById(savedVisit.getId()).get().getDepartmentCode())
                .isEqualTo(updatedVisit.getDepartmentCode());
        assertThat(visitRepository.findById(savedVisit.getId()).get().getTypeCode())
                .isEqualTo(updatedVisit.getTypeCode());
    }

    @Test
    @DisplayName("방문정보를 조회한다.")
    @Transactional
    void findVisit() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자Z","M","1996-11-27","010-1234-5678",1L);
        CreatePatientResponse savedPatient = patientSerivice.create(createPatientRequest);
        CreateVisitRequest visitByPatient = new CreateVisitRequest("1", "01", "D", 1L, savedPatient.getId());
        CreateVisitResponse savedVisit = visitService.create(visitByPatient);
        // when
        FindVisitResponse findVisitResponse = visitService.find(savedVisit.getId());
        // then
        assertThat(findVisitResponse).isNotNull();
    }

    @Test
    @DisplayName("방문정보를 삭제한다.")
    @Transactional
    void deleteVisit() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자I","M","1996-11-27","010-1234-5678",1L);
        CreatePatientResponse savedPatient = patientSerivice.create(createPatientRequest);
        CreateVisitRequest visitByPatient = new CreateVisitRequest("1", "01", "D", 1L, savedPatient.getId());
        CreateVisitResponse savedVisit = visitService.create(visitByPatient);
        // when
        visitService.delete(savedVisit.getId());
        // then
        Optional<Visit> deletedPatient = visitRepository.findById(savedVisit.getId());
        assertThat(deletedPatient.get().getDeletedDate()).isNotNull();
    }
}