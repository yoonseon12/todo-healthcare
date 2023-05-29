package com.healthcare.todohealthcare.controller;

import com.healthcare.todohealthcare.entitiy.dto.*;
import com.healthcare.todohealthcare.service.PatientSerivice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientSerivice patientSerivice;

    @PostMapping("/create")
    public CreatePatientResponse create(@RequestBody CreatePatientRequest createPatientRequest) {
        return patientSerivice.create(createPatientRequest);
    }

    @PutMapping("/update/{id}")
    public UpdatePatientResponse update(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest) {
        return patientSerivice.update(id, updatePatientRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        patientSerivice.delete(id);
    }

    @GetMapping("/find/{id}")
    public FindPatientResponse find(@PathVariable Long id) {
        return patientSerivice.find(id);
    }

    @GetMapping("/findAll")
    public List<FindAllPatientResponse> find() {
        return patientSerivice.findAll();
    }
}
