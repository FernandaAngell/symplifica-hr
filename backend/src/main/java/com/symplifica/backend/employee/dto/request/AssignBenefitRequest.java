package com.symplifica.backend.employee.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AssignBenefitRequest {

    private UUID employeeId;
    private UUID benefitId;
    private BigDecimal amount;
}