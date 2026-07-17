package com.symplifica.backend.benefit.entity;

import com.symplifica.backend.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "benefits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Benefit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal value;

    @Column(nullable = false)
    private Boolean mandatory;
}