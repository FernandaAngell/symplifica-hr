package com.symplifica.backend.employee.repository;

import com.symplifica.backend.employee.entity.EmployeeBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeBenefitRepository extends JpaRepository<EmployeeBenefit, UUID> {

    List<EmployeeBenefit> findByEmployeeId(UUID employeeId);

    boolean existsByEmployeeIdAndBenefitId(UUID employeeId, UUID benefitId);
    void deleteByEmployeeId(UUID employeeId);
}