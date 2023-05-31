package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.dto.*;
import com.healthcare.todohealthcare.dto.search.PatientSearchConditon;
import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
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
    public UpdatePatientResponse update(Long id, UpdatePatientRequest updatePatientRequest) {
        Hospital hospital = hospitalRepository.findById(updatePatientRequest.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("병원 정보가 없습니다."));
        Patient findPatient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 환자 정보가 없습니다."));
        Patient toEntity = updatePatientRequest.toEntity(updatePatientRequest, hospital);
        isDuplicatedPatient(toEntity);

        findPatient.changePatient(toEntity);

        return UpdatePatientResponse.toDTO(findPatient);
    }

    @Transactional
    public void delete(Long id) {
        Patient findPatient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 환자 정보가 없습니다."));
        patientRepository.delete(findPatient);
    }

    public FindPatientResponse find(Long id) {
        Patient findPatient = patientRepository.findPatientWithHospitalWithVisitById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 환자 정보가 없습니다."));
        return FindPatientResponse.toDTO(findPatient);
    }

    public FindAllPatientResponse findAll(PatientSearchConditon searchConditon) {
        List<Patient> patients = patientRepository.searchPatient(searchConditon);
        return FindAllPatientResponse.toDTO(patients);
    }
}
