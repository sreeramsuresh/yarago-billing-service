package com.yarago.billing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceMasterDTO {
    private Long id;

    @NotBlank
    private String serviceCode;

    @NotBlank
    private String serviceName;

    private String description;

    @NotBlank
    private String category;

    @NotNull
    private BigDecimal basePrice;

    private BigDecimal taxPercent;
    private String hsnCode;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
