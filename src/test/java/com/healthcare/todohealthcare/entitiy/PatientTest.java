package com.healthcare.todohealthcare.entitiy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    @Test
    @DisplayName("환자등록번호 생성 및 객체 할당을 확인한다.")
    void 환자등록번호_생성() {
        // given
        Patient patient1 = Patient.of("환자", null, "M", "1990-01-01", "010-1234-5678");
        int count = 1234;

        // when
        Patient result = patient1.createRegistrationNo(count);

        // then
        assertThat(result.getRegistrationNo().length()).isEqualTo(10);
    }

    @Test
    @DisplayName("중복된 환자를 검증한다.")
    void 중복환자검증() {
        // given
        Patient patient1 =
                Patient.of("환자", null, "M", "1990-01-01", "010-1234-5678");
        Patient patient2 =
                Patient.of("환자", null, "M", "1990-01-01", "010-1234-5678");
        // when

        // then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> patient1.isDuplicate(patient2));
        assertThat(exception.getMessage()).isEqualTo("중복된 환자가 존재합니다.");
    }
}