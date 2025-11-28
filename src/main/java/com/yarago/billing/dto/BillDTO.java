package com.yarago.billing.dto;

import com.yarago.billing.entity.Bill;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Long id;
    private String billNumber;

    @NotNull
    private Long patientId;

    @NotNull
    private String patientUhid;

    @NotNull
    private String patientName;

    private Long appointmentId;
    private Long consultationId;
    private LocalDate billDate;
    private LocalDate dueDate;
    private BigDecimal subtotal;
    private BigDecimal discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal taxPercent;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal balanceAmount;
    private Bill.PaymentStatus paymentStatus;
    private Bill.BillType billType;
    private String notes;
    private String createdBy;

    @Builder.Default
    private List<BillItemDTO> items = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
