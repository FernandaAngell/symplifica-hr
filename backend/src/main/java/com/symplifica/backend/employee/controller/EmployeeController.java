package com.symplifica.backend.employee.controller;

import com.symplifica.backend.employee.dto.request.CreateEmployeeRequest;
import com.symplifica.backend.employee.dto.response.EmployeeResponse;
import com.symplifica.backend.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public EmployeeResponse save(@RequestBody CreateEmployeeRequest request) {
        return service.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
    service.delete(id);
}

@PutMapping("/{id}")
public EmployeeResponse update(
        @PathVariable UUID id,
        @RequestBody CreateEmployeeRequest request) {

    return service.update(id, request);
}

}