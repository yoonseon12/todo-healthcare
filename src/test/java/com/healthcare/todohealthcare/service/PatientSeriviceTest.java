package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.dto.*;
import com.healthcare.todohealthcare.dto.search.PatientSearchConditon;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.repository.PatientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional(readOnly = true)
@Rollback(false)
class PatientSeriviceTest {
    @Autowired
    private PatientSerivice patientSerivice;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("환자를 생성한다.")
    @Transactional
    void createPatient() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자Z","남","1996-11-27","010-1234-5678",1L);
        // when

        // then
        assertThatCode(() -> patientSerivice.create(createPatientRequest)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("환자 정보를 수정한다.")
    @Transactional
    void updatePatient() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자T","M","1996-11-27","010-1234-5678",1L);
        CreatePatientResponse savedPatient = patientSerivice.create(createPatientRequest);
        UpdatePatientRequest updatePatientRequest =
                new UpdatePatientRequest("이름수정","F","1996-11-27","010-1234-5678",2L);
        // when
        UpdatePatientResponse updatedPatient = patientSerivice.update(savedPatient.getId(), updatePatientRequest);
        // then
        assertThat(patientRepository.findById(savedPatient.getId()).get().getName())
                .isEqualTo(updatedPatient.getName());
        assertThat(patientRepository.findById(savedPatient.getId()).get().getGender())
                .isEqualTo(updatedPatient.getGender());
    }

    @Test
    @DisplayName("환자를 삭제한다.")
    @Transactional
    void deletePatient() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자A","남","1996-11-27","010-1234-5678",1L);
        CreatePatientResponse savedPatient = patientSerivice.create(createPatientRequest);
        // when
        patientSerivice.delete(savedPatient.getId());
        // then
        Optional<Patient> deletedPatient = patientRepository.findById(savedPatient.getId());
        assertTrue(deletedPatient.isEmpty());
    }

    @Test
    @DisplayName("환자 정보를 조회한다.")
    void find() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자A","남","1996-11-27","010-1234-5678",1L);
        CreatePatientResponse savedPatient = patientSerivice.create(createPatientRequest);
        // when
        FindPatientResponse findPatientA = patientSerivice.find(savedPatient.getId());
        // then
        assertThat(findPatientA).isNotNull();
    }

    @Test
    @DisplayName("전체 환자 정보 조회")
    void findAll() {
        // given
        CreatePatientRequest PatientA =
                new CreatePatientRequest("환자A","M","1996-11-27","010-1234-5678",1L);
        CreatePatientRequest PatientB =
                new CreatePatientRequest("환자B","F","1996-11-27","010-1234-5678",1L);
        CreatePatientRequest PatientC =
                new CreatePatientRequest("환자C","F","1996-11-27","010-1234-5678",1L);
        CreatePatientRequest PatientD =
                new CreatePatientRequest("환자D","F","1996-11-23","010-1234-5678",1L);
        CreatePatientRequest PatientE =
                new CreatePatientRequest("환자E","F","1996-11-23","010-1234-5678",1L);
        patientSerivice.create(PatientA);
        patientSerivice.create(PatientB);
        patientSerivice.create(PatientC);
        patientSerivice.create(PatientD);
        patientSerivice.create(PatientE);
        // when
        PatientSearchConditon searchConditon = new PatientSearchConditon(null, null, "1996-11-27");
        FindAllPatientResponse result = patientSerivice.findAll(searchConditon);
        // then
        assertThat(result.getPatients().size()).isEqualTo(3);
    }
}