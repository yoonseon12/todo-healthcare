package com.healthcare.todohealthcare.controller;

import com.healthcare.todohealthcare.entitiy.dto.*;
import com.healthcare.todohealthcare.service.PatientSerivice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
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

    @PatchMapping("/update")
    public UpdatePatientResponse update(@RequestBody UpdatePatientRequest updatePatientRequest) {
        return patientSerivice.update(updatePatientRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        patientSerivice.delete(id);
    }
}
