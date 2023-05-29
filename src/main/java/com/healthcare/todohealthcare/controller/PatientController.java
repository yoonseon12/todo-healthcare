package com.healthcare.todohealthcare.controller;

import com.healthcare.todohealthcare.dto.*;
import com.healthcare.todohealthcare.dto.commenResponse.CommonResponse;
import com.healthcare.todohealthcare.service.PatientSerivice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientSerivice patientSerivice;

    @PostMapping
    public CommonResponse<CreatePatientResponse> create(@RequestBody CreatePatientRequest createPatientRequest) {
        return CommonResponse.of(patientSerivice.create(createPatientRequest));
    }

    @PutMapping("/{id}")
    public CommonResponse<UpdatePatientResponse> update(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest) {
        return CommonResponse.of(patientSerivice.update(id, updatePatientRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientSerivice.delete(id);
    }

    @GetMapping("/{id}")
    public CommonResponse<FindPatientResponse> find(@PathVariable Long id) {
        return CommonResponse.of(patientSerivice.find(id));
    }

    @GetMapping
    public CommonResponse<List<FindAllPatientResponse>> findAll() {
        return CommonResponse.of(patientSerivice.findAll());
    }
}
