package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.entitiy.dto.CreateVisitRequest;
import com.healthcare.todohealthcare.entitiy.dto.CreateVisitResponse;
import com.healthcare.todohealthcare.entitiy.dto.UpdateVisitRequest;
import com.healthcare.todohealthcare.entitiy.dto.UpdateVisitResponse;
import com.healthcare.todohealthcare.repository.HospitalRepository;
import com.healthcare.todohealthcare.repository.PatientRepository;
import com.healthcare.todohealthcare.repository.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
    @DisplayName("방문정보를 수정한다.")
    @Transactional
    void updateVisit() {
        // given
        Hospital hospital = new Hospital("G병원","90","G병원장");
        Hospital savedHospital = hospitalRepository.save(hospital);
        Patient patient = new Patient("환자A",null, "M", "1990-01-01", "010-1234-5678", hospital);
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
}