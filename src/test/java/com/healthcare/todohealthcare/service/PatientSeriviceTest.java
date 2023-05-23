package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.entitiy.dto.CreatePatientRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@Transactional(readOnly = true)

class PatientSeriviceTest {
    @Autowired
    private PatientSerivice patientSerivice;

    @Test
    @DisplayName("환자를 생성한다.")
    @Transactional
    void createPatient() {
        // given
        CreatePatientRequest createPatientRequest =
                new CreatePatientRequest("환자A","남","1996-11-27","010-1234-5678",1L);
        // when

        // then
        assertThatCode(() -> patientSerivice.create(createPatientRequest)).doesNotThrowAnyException();
    }
}