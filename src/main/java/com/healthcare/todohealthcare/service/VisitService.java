package com.healthcare.todohealthcare.service;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.healthcare.todohealthcare.entitiy.Visit;
import com.healthcare.todohealthcare.entitiy.dto.CreateVisitRequest;
import com.healthcare.todohealthcare.entitiy.dto.CreateVisitResponse;
import com.healthcare.todohealthcare.entitiy.dto.UpdateVisitRequest;
import com.healthcare.todohealthcare.entitiy.dto.UpdateVisitResponse;
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
}
