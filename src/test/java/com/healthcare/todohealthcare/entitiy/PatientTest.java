package com.healthcare.todohealthcare.entitiy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientTest {
    @Test
    @DisplayName("환자등록번호 생성 및 객체 할당을 확인한다.")
    void 환자등록번호_생성() {
        // given
        Hospital hospitalC = new Hospital(100L, "병원C", "11", "C병원장");
        int countByhospitalC = 1234;
        Hospital hospitalD = new Hospital(101L, "병원D", "12", "D병원장");
        int countByhospitalD = 1234;
        Patient patientA = Patient.of("환자", null, "M", "1990-01-01", "010-1234-5678", hospitalC);
        Patient patientB = Patient.of("환자", null, "M", "1990-01-01", "010-1234-5678", hospitalD);

        // when
        Patient result1 = patientA.createRegistrationNo(hospitalC.getCareCenterNo(), countByhospitalC);
        Patient result2 = patientA.createRegistrationNo(hospitalD.getCareCenterNo(), countByhospitalD);

        // then
        assertThat(result1.getRegistrationNo().length()).isEqualTo(10);
        assertThat(result2.getRegistrationNo().length()).isEqualTo(10);
    }

    @Test
    @DisplayName("중복 있는 환자를 검증한다.")
    void 중복환자검증_중복있음() {
        // given
        Hospital hospital = new Hospital(99L, "병원C", "11", "C병원장");
        Patient patient1 =
                Patient.of("환자", null, "M", "1990-01-01", "010-1234-5678", hospital);
        Patient patient2 =
                Patient.of("환자", null, "M", "1990-01-01", "010-1234-5678", hospital);
        // when

        // then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> patient1.isDuplicate(patient2));
        assertThat(exception.getMessage()).isEqualTo("병원 내 중복된 환자가 존재합니다.");
    }

    @Test
    @DisplayName("중복 없는 환자를 검증한다.")
    void 중복환자검증_중복없음() {
        // given
        Hospital hospitalC = new Hospital(99L, "병원C", "11", "C병원장");
        Hospital hospitalD = new Hospital(100L, "병원D", "12", "D병원장");
        Patient patient1 =
                Patient.of("환자A", null, "M", "1990-01-01", "010-1234-5678", hospitalC);
        Patient patient2 =
                Patient.of("환자A", null, "M", "1990-01-01", "010-1234-5678", hospitalD);
        // when

        // then
        assertThatCode(() -> patient1.isDuplicate(patient2)).doesNotThrowAnyException();
    }
}