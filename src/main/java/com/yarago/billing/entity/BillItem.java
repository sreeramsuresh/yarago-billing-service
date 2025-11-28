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
 * Entity representing individual line items in a bill
 */
@Entity
@Table(name = "bill_items", indexes = {
    @Index(name = "idx_bill_item_bill", columnList = "bill_id"),
    @Index(name = "idx_bill_item_type", columnList = "itemType"),
    @Index(name = "idx_bill_item_code", columnList = "itemCode")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ItemType itemType;

    @Column(length = 50)
    private String itemCode;

    @Column(nullable = false, length = 200)
    private String itemName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal discountPercent = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal taxPercent = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(length = 20)
    private String hsnCode; // HSN/SAC code for GST

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum ItemType {
        CONSULTATION_FEE,
        REGISTRATION_FEE,
        PROCEDURE,
        SURGERY,
        INVESTIGATION,
        MEDICINE,
        LENS,
        FRAME,
        CONTACT_LENS,
        TEST,
        PACKAGE,
        OTHER
    }

    // Helper method to calculate total
    public void calculateTotal() {
        BigDecimal baseAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));

        // Apply discount
        if (discountPercent != null && discountPercent.compareTo(BigDecimal.ZERO) > 0) {
            discountAmount = baseAmount.multiply(discountPercent).divide(BigDecimal.valueOf(100));
        }

        BigDecimal amountAfterDiscount = baseAmount.subtract(discountAmount != null ? discountAmount : BigDecimal.ZERO);

        // Apply tax
        if (taxPercent != null && taxPercent.compareTo(BigDecimal.ZERO) > 0) {
            taxAmount = amountAfterDiscount.multiply(taxPercent).divide(BigDecimal.valueOf(100));
        }

        totalPrice = amountAfterDiscount.add(taxAmount != null ? taxAmount : BigDecimal.ZERO);
    }
}
