package com.symplifica.backend.benefit.repository;

import com.symplifica.backend.benefit.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BenefitRepository extends JpaRepository<Benefit, UUID> {
}