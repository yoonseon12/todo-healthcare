package com.healthcare.todohealthcare.controller;

import com.healthcare.todohealthcare.entitiy.dto.*;
import com.healthcare.todohealthcare.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visit")
public class VisitController {
    private final VisitService visitService;

    @PostMapping("/create")
    public CreateVisitResponse create(@RequestBody CreateVisitRequest createVisitRequest) {
        return visitService.create(createVisitRequest);
    }

    @PutMapping("/update/{id}")
    public UpdateVisitResponse update(@PathVariable Long id, @RequestBody UpdateVisitRequest updateVisitRequest) {
        return visitService.update(id, updateVisitRequest);
    }
}
