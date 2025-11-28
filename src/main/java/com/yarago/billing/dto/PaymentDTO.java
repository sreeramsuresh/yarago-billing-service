package com.yarago.billing.dto;

import com.yarago.billing.entity.Payment;
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
public class PaymentDTO {
    private Long id;
    private String paymentNumber;

    @NotNull
    private Long billId;

    @NotNull
    private Long patientId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Payment.PaymentMethod paymentMethod;

    private LocalDateTime paymentDate;
    private String transactionId;
    private String referenceNumber;
    private Payment.PaymentStatus status;
    private String receivedBy;
    private String notes;
    private LocalDateTime createdAt;
}
