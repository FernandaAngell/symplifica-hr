package com.symplifica.backend.employee.entity;

import com.symplifica.backend.benefit.entity.Benefit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "employee_benefits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeBenefit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "benefit_id", nullable = false)
    private Benefit benefit;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
}