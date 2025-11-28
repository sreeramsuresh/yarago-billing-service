package com.yarago.billing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a payment transaction
 */
@Entity
@Table(name = "payments", indexes = {
    @Index(name = "idx_payment_number", columnList = "paymentNumber"),
    @Index(name = "idx_payment_bill", columnList = "bill_id"),
    @Index(name = "idx_payment_patient", columnList = "patientId"),
    @Index(name = "idx_payment_date", columnList = "paymentDate"),
    @Index(name = "idx_payment_status", columnList = "status")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String paymentNumber; // PAY-YYYYMMDD-XXXX

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(length = 100)
    private String transactionId; // For card/UPI transactions

    @Column(length = 100)
    private String referenceNumber; // Bank reference/cheque number

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentStatus status;

    @Column(length = 100)
    private String receivedBy; // Staff who received payment

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum PaymentMethod {
        CASH,
        CARD,
        UPI,
        NETBANKING,
        INSURANCE,
        WALLET,
        CHEQUE,
        OTHER
    }

    public enum PaymentStatus {
        SUCCESS,
        FAILED,
        PENDING,
        REFUNDED
    }
}
