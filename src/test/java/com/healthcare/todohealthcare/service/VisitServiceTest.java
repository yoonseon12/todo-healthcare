package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.entitiy.Visit;
import com.healthcare.todohealthcare.entitiy.dto.*;
import com.healthcare.todohealthcare.repository.HospitalRepository;
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
    private HospitalRepository hospitalRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("방문정보를 생성한다.")
    @Transactional
    void createVisit() {
        // given
        Hospital hospitalG = new Hospital("G병원","90","G병원장");
        Hospital hospitalH = new Hospital("H병원","91","H병원장");
        Hospital savedHospitalG = hospitalRepository.save(hospitalG);
        Hospital savedHospitalH = hospitalRepository.save(hospitalH);
        Patient patientA = new Patient("환자A",null, "M", "1990-01-01", "010-1234-5678", hospitalG);
        Patient patientB = new Patient("환자B",null, "F", "1990-01-01", "010-1234-5678", hospitalH);
        Patient savedPatientA = patientRepository.save(patientA);
        Patient savedPatientB = patientRepository.save(patientB);
        CreateVisitRequest visitByPatientA = new CreateVisitRequest("1", "01", "D", savedHospitalG.getId(), savedPatientA.getId());
        CreateVisitRequest visitByPatientB = new CreateVisitRequest("1", "02", "T", savedHospitalH.getId(), savedPatientB.getId());
        // when

        // then
        assertThatCode(() -> visitService.create(visitByPatientA)).doesNotThrowAnyException();
        assertThatCode(() -> visitService.create(visitByPatientB)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("방문정보 생성 시 병원에 속해있는 환자를 검증한다.")
    void createVisitValidate() {
        // given
        Hospital hospitalG = new Hospital("G병원","90","G병원장");
        Hospital hospitalH = new Hospital("H병원","91","H병원장");
        Hospital savedHospitalG = hospitalRepository.save(hospitalG);
        Hospital savedHospitalH = hospitalRepository.save(hospitalH);
        Patient patientA = new Patient("환자A",null, "M", "1990-01-01", "010-1234-5678", hospitalG);
        Patient patientB = new Patient("환자B",null, "F", "1990-01-01", "010-1234-5678", hospitalH);
        Patient savedPatientA = patientRepository.save(patientA);
        Patient savedPatientB = patientRepository.save(patientB);

        CreateVisitRequest visitByPatientA = new CreateVisitRequest("1", "01", "D", savedHospitalG.getId(), savedPatientB.getId());
        // when

        // then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> visitService.create(visitByPatientA));
        assertThat(exception.getMessage()).isEqualTo("해당 병원에 등록된 환자가 없습니다.");
    }


    @Test
    @DisplayName("방문정보를 수정한다.")
    @Transactional
    void updateVisit() {
        // given
        Hospital hospital = new Hospital("G병원","90","G병원장");
        Hospital savedHospital = hospitalRepository.save(hospital);
        Patient patient = new Patient("환자A", null, "M", "1990-01-01", "010-1234-5678", hospital);
        Patient savedPatient = patientRepository.save(patient);
        CreateVisitRequest visitByPatient = new CreateVisitRequest("1", "01", "D", savedHospital.getId(), savedPatient.getId());
        CreateVisitResponse savedVisit = visitService.create(visitByPatient);

        UpdateVisitRequest updateVisitRequest = new UpdateVisitRequest("2", "02", "T", savedHospital.getId(), savedPatient.getId());
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
        Hospital hospital = new Hospital("G병원","90","G병원장");
        Hospital savedHospital = hospitalRepository.save(hospital);
        Patient patient = new Patient("환자A",null, "M", "1990-01-01", "010-1234-5678", hospital);
        Patient savedPatient = patientRepository.save(patient);
        CreateVisitRequest visitByPatient = new CreateVisitRequest("1", "01", "D", savedHospital.getId(), savedPatient.getId());
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
        Hospital hospital = new Hospital("G병원","90","G병원장");
        Hospital savedHospital = hospitalRepository.save(hospital);
        Patient patient = new Patient("환자A",null, "M", "1990-01-01", "010-1234-5678", hospital);
        Patient savedPatient = patientRepository.save(patient);
        CreateVisitRequest visitByPatient = new CreateVisitRequest("1", "01", "D", savedHospital.getId(), savedPatient.getId());
        CreateVisitResponse savedVisit = visitService.create(visitByPatient);
        // when
        visitService.delete(savedVisit.getId());
        // then
        Optional<Visit> deletedPatient = visitRepository.findById(savedVisit.getId());
        assertTrue(deletedPatient.isEmpty());
    }
}