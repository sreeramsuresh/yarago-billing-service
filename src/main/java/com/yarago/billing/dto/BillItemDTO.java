package com.yarago.billing.dto;

import com.yarago.billing.entity.BillItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillItemDTO {
    private Long id;

    @NotNull
    private BillItem.ItemType itemType;

    private String itemCode;

    @NotNull
    private String itemName;

    private String description;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal unitPrice;

    private BigDecimal discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal taxPercent;
    private BigDecimal taxAmount;
    private BigDecimal totalPrice;
    private String hsnCode;
}
