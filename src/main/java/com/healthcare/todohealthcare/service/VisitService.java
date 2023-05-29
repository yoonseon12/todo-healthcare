package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.entitiy.Visit;
import com.healthcare.todohealthcare.entitiy.dto.*;
import com.healthcare.todohealthcare.repository.HospitalRepository;
import com.healthcare.todohealthcare.repository.PatientRepository;
import com.healthcare.todohealthcare.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitService {
    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public CreateVisitResponse create(CreateVisitRequest createVisitRequest) {
        Patient patient = patientRepository.findPatientByHospitalIdAndPatientId(createVisitRequest.getPatientId(), createVisitRequest.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("해당 병원에 등록된 환자가 없습니다."));
        Hospital findHospital = hospitalRepository.findById(createVisitRequest.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("병원 정보가 없습니다."));
        Patient findPatient = patientRepository.findById(createVisitRequest.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 환자 정보가 없습니다."));

        Visit toEntity = createVisitRequest.toEntity(createVisitRequest, findHospital, findPatient);

        Visit visit = visitRepository.save(toEntity);

        findPatient.changeLastVisitDate(); // 환자의 최근 방문일시 업데이트

        return CreateVisitResponse.toDTO(visit);
    }

    @Transactional
    public UpdateVisitResponse update(Long id, UpdateVisitRequest updateVisitRequest) {
        Hospital findHospital = hospitalRepository.findById(updateVisitRequest.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("병원 정보가 없습니다."));
        Patient findPatient = patientRepository.findById(updateVisitRequest.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 환자 정보가 없습니다."));
        Visit findVisit = visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 방문 정보가 없습니다."));
        Visit toEntity = updateVisitRequest.toEntity(updateVisitRequest, findHospital, findPatient);

        findVisit.changeVisit(toEntity);

        return UpdateVisitResponse.toDTO(findVisit);
    }

    public FindVisitResponse find(Long id){
        Visit findVisit = visitRepository.findVisitWithHospitalWithPatientById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 방문 정보가 없습니다."));
        return FindVisitResponse.toDTO(findVisit);
    }

    @Transactional
    public void delete(Long id) {
        Visit findVisit = visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식별자의 방문 정보가 없습니다."));
        visitRepository.delete(findVisit);
    }
}
