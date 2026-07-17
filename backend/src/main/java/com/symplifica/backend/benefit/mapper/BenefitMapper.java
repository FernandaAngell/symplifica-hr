package com.symplifica.backend.benefit.mapper;

import com.symplifica.backend.benefit.dto.request.CreateBenefitRequest;
import com.symplifica.backend.benefit.dto.response.BenefitResponse;
import com.symplifica.backend.benefit.entity.Benefit;

public class BenefitMapper {

    private BenefitMapper() {
    }

    public static Benefit toEntity(CreateBenefitRequest request) {

        return Benefit.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .value(request.getValue())
                .mandatory(request.getMandatory())
                .build();
    }

    public static BenefitResponse toResponse(Benefit benefit) {

        return BenefitResponse.builder()
                .id(benefit.getId())
                .name(benefit.getName())
                .description(benefit.getDescription())
                .category(benefit.getCategory())
                .value(benefit.getValue())
                .mandatory(benefit.getMandatory())
                .build();
    }
}