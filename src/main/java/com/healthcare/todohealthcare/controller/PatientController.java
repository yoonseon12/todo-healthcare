package com.healthcare.todohealthcare.controller;

import com.healthcare.todohealthcare.entitiy.dto.CreatePatientRequest;
import com.healthcare.todohealthcare.entitiy.dto.CreatePatientResponse;
import com.healthcare.todohealthcare.service.PatientSerivice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientSerivice patientSerivice;

    @PostMapping("/create")
    public CreatePatientResponse create(@RequestBody CreatePatientRequest createPatientRequest) {
        return patientSerivice.create(createPatientRequest);
    }
}
