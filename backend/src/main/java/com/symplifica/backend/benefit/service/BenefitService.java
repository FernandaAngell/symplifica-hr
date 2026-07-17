package com.symplifica.backend.benefit.service;

import com.symplifica.backend.benefit.dto.request.CreateBenefitRequest;
import com.symplifica.backend.benefit.dto.response.BenefitResponse;
import com.symplifica.backend.benefit.entity.Benefit;
import com.symplifica.backend.benefit.mapper.BenefitMapper;
import com.symplifica.backend.benefit.repository.BenefitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BenefitService {

    private final BenefitRepository repository;

    public BenefitService(BenefitRepository repository) {
        this.repository = repository;
    }

    public List<BenefitResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(BenefitMapper::toResponse)
                .toList();
    }

    public BenefitResponse save(CreateBenefitRequest request) {

        Benefit benefit = BenefitMapper.toEntity(request);

        benefit = repository.save(benefit);

        return BenefitMapper.toResponse(benefit);
    }

    public BenefitResponse update(UUID id, CreateBenefitRequest request) {

    Benefit benefit = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Beneficio no encontrado"));

    benefit.setName(request.getName());
    benefit.setDescription(request.getDescription());
    benefit.setCategory(request.getCategory());
    benefit.setValue(request.getValue());
    benefit.setMandatory(request.getMandatory());

    benefit = repository.save(benefit);

    return BenefitMapper.toResponse(benefit);
}

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}