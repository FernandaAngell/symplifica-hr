package com.symplifica.backend.benefit.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record BenefitResponse(

        UUID id,
        String name,
        String description,
        String category,
        BigDecimal value,
        Boolean mandatory

) {
}