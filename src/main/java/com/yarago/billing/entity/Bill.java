package com.yarago.billing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a bill/invoice
 */
@Entity
@Table(name = "bills", indexes = {
    @Index(name = "idx_bill_number", columnList = "billNumber"),
    @Index(name = "idx_bill_patient", columnList = "patientUhid"),
    @Index(name = "idx_bill_date", columnList = "billDate"),
    @Index(name = "idx_payment_status", columnList = "paymentStatus")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String billNumber; // BIL-YYYYMMDD-XXXX

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false, length = 50)
    private String patientUhid;

    @Column(nullable = false, length = 200)
    private String patientName;

    private Long appointmentId;

    private Long consultationId;

    @Column(nullable = false)
    private LocalDate billDate;

    private LocalDate dueDate;

    // Amounts
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 5, scale = 2)
    private BigDecimal discountPercent;

    @Column(precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(precision = 5, scale = 2)
    private BigDecimal taxPercent;

    @Column(precision = 10, scale = 2)
    private BigDecimal taxAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal balanceAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private BillType billType;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(length = 100)
    private String createdBy;

    // Bill Items (One-to-Many relationship)
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<BillItem> items = new ArrayList<>();

    // Payments (One-to-Many relationship)
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum PaymentStatus {
        PENDING,
        PARTIAL,
        PAID,
        CANCELLED,
        REFUNDED
    }

    public enum BillType {
        CONSULTATION,
        PROCEDURE,
        PHARMACY,
        OPTICAL,
        PACKAGE,
        MIXED
    }

    // Helper methods
    public void addItem(BillItem item) {
        items.add(item);
        item.setBill(this);
    }

    public void removeItem(BillItem item) {
        items.remove(item);
        item.setBill(null);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setBill(this);
    }

    public void calculateTotals() {
        // Calculate subtotal from items
        this.subtotal = items.stream()
            .map(BillItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate discount amount
        if (discountPercent != null && discountPercent.compareTo(BigDecimal.ZERO) > 0) {
            this.discountAmount = subtotal.multiply(discountPercent).divide(BigDecimal.valueOf(100));
        } else if (discountAmount == null) {
            this.discountAmount = BigDecimal.ZERO;
        }

        // Calculate tax amount
        BigDecimal amountAfterDiscount = subtotal.subtract(discountAmount);
        if (taxPercent != null && taxPercent.compareTo(BigDecimal.ZERO) > 0) {
            this.taxAmount = amountAfterDiscount.multiply(taxPercent).divide(BigDecimal.valueOf(100));
        } else {
            this.taxAmount = BigDecimal.ZERO;
        }

        // Calculate total
        this.totalAmount = amountAfterDiscount.add(taxAmount);

        // Calculate balance
        if (paidAmount == null) {
            paidAmount = BigDecimal.ZERO;
        }
        this.balanceAmount = totalAmount.subtract(paidAmount);

        // Update payment status
        updatePaymentStatus();
    }

    private void updatePaymentStatus() {
        if (balanceAmount.compareTo(BigDecimal.ZERO) == 0) {
            this.paymentStatus = PaymentStatus.PAID;
        } else if (paidAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.paymentStatus = PaymentStatus.PARTIAL;
        } else {
            this.paymentStatus = PaymentStatus.PENDING;
        }
    }
}
