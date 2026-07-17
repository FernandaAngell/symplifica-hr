package com.symplifica.backend.employee.service;

import com.symplifica.backend.benefit.entity.Benefit;
import com.symplifica.backend.benefit.repository.BenefitRepository;
import com.symplifica.backend.employee.dto.request.AssignBenefitRequest;
import com.symplifica.backend.employee.dto.response.EmployeeBenefitResponse;
import com.symplifica.backend.employee.entity.Employee;
import com.symplifica.backend.employee.entity.EmployeeBenefit;
import com.symplifica.backend.employee.repository.EmployeeBenefitRepository;
import com.symplifica.backend.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeBenefitService {

    private final EmployeeBenefitRepository employeeBenefitRepository;
    private final EmployeeRepository employeeRepository;
    private final BenefitRepository benefitRepository;

    public EmployeeBenefitService(
            EmployeeBenefitRepository employeeBenefitRepository,
            EmployeeRepository employeeRepository,
            BenefitRepository benefitRepository) {
        this.employeeBenefitRepository = employeeBenefitRepository;
        this.employeeRepository = employeeRepository;
        this.benefitRepository = benefitRepository;
    }

    public void assignBenefit(AssignBenefitRequest request) {

        if (employeeBenefitRepository.existsByEmployeeIdAndBenefitId(
                request.getEmployeeId(),
                request.getBenefitId())) {
            throw new RuntimeException("Este beneficio ya está asignado al empleado.");
        }

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Benefit benefit = benefitRepository.findById(request.getBenefitId())
                .orElseThrow(() -> new RuntimeException("Beneficio no encontrado"));

        EmployeeBenefit employeeBenefit = EmployeeBenefit.builder()
                .employee(employee)
                .benefit(benefit)
                .amount(request.getAmount())
                .build();

        employeeBenefitRepository.save(employeeBenefit);
    }

    public List<EmployeeBenefitResponse> findByEmployee(UUID employeeId) {

        return employeeBenefitRepository.findByEmployeeId(employeeId)
                .stream()
                .map(employeeBenefit -> EmployeeBenefitResponse.builder()
                        .id(employeeBenefit.getId())
                        .benefitId(employeeBenefit.getBenefit().getId())
                        .benefitName(employeeBenefit.getBenefit().getName())
                        .description(employeeBenefit.getBenefit().getDescription())
                        .category(employeeBenefit.getBenefit().getCategory())
                        .amount(employeeBenefit.getAmount())
                        .build())
                .toList();
    }
}