package com.healthcare.todohealthcare.controller;

import com.healthcare.todohealthcare.dto.*;
import com.healthcare.todohealthcare.dto.commenResponse.CommonResponse;
import com.healthcare.todohealthcare.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visits")
public class VisitController {
    private final VisitService visitService;

    @PostMapping
    public CommonResponse<CreateVisitResponse> create(@RequestBody CreateVisitRequest createVisitRequest) {
        return CommonResponse.of(visitService.create(createVisitRequest));
    }

    @PutMapping("/{id}")
    public CommonResponse<UpdateVisitResponse> update(@PathVariable Long id, @RequestBody UpdateVisitRequest updateVisitRequest) {
        return CommonResponse.of(visitService.update(id, updateVisitRequest));
    }

    @GetMapping("/{id}")
    public CommonResponse<FindVisitResponse> find(@PathVariable Long id) {
        return CommonResponse.of(visitService.find(id));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<DeleteVisitResponse> delete(@PathVariable Long id) {
        return CommonResponse.of(visitService.delete(id));
    }
}
