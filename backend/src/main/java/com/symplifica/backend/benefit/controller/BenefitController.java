package com.symplifica.backend.benefit.controller;

import com.symplifica.backend.benefit.dto.request.CreateBenefitRequest;
import com.symplifica.backend.benefit.dto.response.BenefitResponse;
import com.symplifica.backend.benefit.service.BenefitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benefits")
@CrossOrigin("*")
public class BenefitController {

    private final BenefitService service;

    public BenefitController(BenefitService service) {
        this.service = service;
    }

    @GetMapping
    public List<BenefitResponse> findAll() {
        return service.findAll();
    }

    @PostMapping
    public BenefitResponse save(@RequestBody CreateBenefitRequest request) {
        return service.save(request);
    }
    
    @PutMapping("/{id}")
public BenefitResponse update(
        @PathVariable java.util.UUID id,
        @RequestBody CreateBenefitRequest request
) {
    return service.update(id, request);
}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable java.util.UUID id) {
        service.delete(id);
    }
}