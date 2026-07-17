package com.symplifica.backend.employee.controller;

import com.symplifica.backend.employee.dto.request.AssignBenefitRequest;
import com.symplifica.backend.employee.dto.response.EmployeeBenefitResponse;
import com.symplifica.backend.employee.service.EmployeeBenefitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.symplifica.backend.employee.dto.response.EmployeeBenefitResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee-benefits")
@CrossOrigin("*")
public class EmployeeBenefitController {

    private final EmployeeBenefitService employeeBenefitService;

    public EmployeeBenefitController(EmployeeBenefitService employeeBenefitService) {
        this.employeeBenefitService = employeeBenefitService;
    }

    @PostMapping
    public ResponseEntity<String> assignBenefit(@RequestBody AssignBenefitRequest request) {

        employeeBenefitService.assignBenefit(request);

        return ResponseEntity.ok("Beneficio asignado correctamente.");
    }
    @GetMapping("/{employeeId}")
public ResponseEntity<List<EmployeeBenefitResponse>> getBenefits(
        @PathVariable UUID employeeId) {

    return ResponseEntity.ok(
            employeeBenefitService.findByEmployee(employeeId)
    );
}
}