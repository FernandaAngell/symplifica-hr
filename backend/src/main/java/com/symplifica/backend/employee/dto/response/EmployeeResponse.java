package com.symplifica.backend.employee.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record EmployeeResponse(

        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String city,
        String position,
        BigDecimal latitude,
        BigDecimal longitude,
        String displayName

) {
}