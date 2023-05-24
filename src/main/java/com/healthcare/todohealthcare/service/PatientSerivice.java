package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.entitiy.dto.CreatePatientRequest;
import com.healthcare.todohealthcare.entitiy.dto.CreatePatientResponse;
import com.healthcare.todohealthcare.entitiy.dto.UpdatePatientRequest;
import com.healthcare.todohealthcare.entitiy.dto.UpdatePatientResponse;
import com.healthcare.todohealthcare.repository.HospitalRepository;
import com.healthcare.todohealthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientSerivice {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Transactional
    public CreatePatientResponse create(CreatePatientRequest createPatientRequest) {
        Hospital hospital = hospitalRepository.findById(createPatientRequest.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("병원 정보가 없습니다."));
        Patient toEntity = createPatientRequest.toEntity(createPatientRequest, hospital);
        isDuplicatedPatient(toEntity);

        int count = patientRepository.countByHospitalId(createPatientRequest.getHospitalId()).intValue();
        Patient patient = toEntity.
                addPatientByhosplitalInfo(count, hospital);

        return CreatePatientResponse
                .toDTO(patientRepository.save(patient));
    }
    private void isDuplicatedPatient(Patient targetPatient) {
        List<Patient> patients = patientRepository.findAll();
        for (Patient patient : patients) {
            targetPatient.isDuplicate(patient);
        }
    }

    @Transactional
    public UpdatePatientResponse update(UpdatePatientRequest updatePatientRequest) {
        Hospital hospital = hospitalRepository.findById(updatePatientRequest.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("병원 정보가 없습니다."));
        Patient findPatient = patientRepository.findById(updatePatientRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 환자 정보가 없습니다."));
        Patient toEntity = updatePatientRequest.toEntity(updatePatientRequest, hospital);
        isDuplicatedPatient(toEntity);

        findPatient.changePatient(toEntity);

        return UpdatePatientResponse.toDTO(findPatient);
    }

}
