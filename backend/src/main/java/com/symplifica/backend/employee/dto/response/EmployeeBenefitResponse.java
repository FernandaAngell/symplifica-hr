package com.symplifica.backend.employee.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class EmployeeBenefitResponse {

    private UUID id;

    private UUID benefitId;

    private String benefitName;

    private String description;

    private String category;

    private BigDecimal amount;
}