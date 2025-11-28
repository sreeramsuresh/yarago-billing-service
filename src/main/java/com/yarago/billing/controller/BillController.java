package com.yarago.billing.controller;

import com.yarago.billing.dto.BillDTO;
import com.yarago.billing.dto.BillItemDTO;
import com.yarago.billing.service.BillService;
import com.yarago.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
@Tag(name = "Billing", description = "Billing management APIs")
public class BillController {

    private final BillService billService;

    @PostMapping
    @Operation(summary = "Create new bill")
    public ResponseEntity<ApiResponse<BillDTO>> createBill(@Valid @RequestBody BillDTO dto) {
        BillDTO created = billService.createBill(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Created successfully", created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get bill by ID")
    public ResponseEntity<ApiResponse<BillDTO>> getBillById(@PathVariable Long id) {
        BillDTO bill = billService.getBillById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", bill));
    }

    @GetMapping("/number/{billNumber}")
    @Operation(summary = "Get bill by bill number")
    public ResponseEntity<ApiResponse<BillDTO>> getBillByNumber(@PathVariable String billNumber) {
        BillDTO bill = billService.getBillByNumber(billNumber);
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", bill));
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get all bills for a patient")
    public ResponseEntity<ApiResponse<List<BillDTO>>> getPatientBills(@PathVariable Long patientId) {
        List<BillDTO> bills = billService.getPatientBills(patientId);
        return ResponseEntity.ok(ApiResponse.success("Bills retrieved successfully", bills));
    }

    @GetMapping
    @Operation(summary = "Get bills by date range")
    public ResponseEntity<ApiResponse<List<BillDTO>>> getBillsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<BillDTO> bills = billService.getBillsByDateRange(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", bills));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update bill")
    public ResponseEntity<ApiResponse<BillDTO>> updateBill(
            @PathVariable Long id,
            @Valid @RequestBody BillDTO dto) {
        BillDTO updated = billService.updateBill(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Updated successfully", updated));
    }

    @PostMapping("/{id}/items")
    @Operation(summary = "Add item to bill")
    public ResponseEntity<ApiResponse<BillDTO>> addItem(
            @PathVariable Long id,
            @Valid @RequestBody BillItemDTO itemDto) {
        BillDTO updated = billService.addItem(id, itemDto);
        return ResponseEntity.ok(ApiResponse.success("Added successfully", updated));
    }

    @PatchMapping("/{id}/cancel")
    @Operation(summary = "Cancel bill")
    public ResponseEntity<ApiResponse<BillDTO>> cancelBill(@PathVariable Long id) {
        BillDTO cancelled = billService.cancelBill(id);
        return ResponseEntity.ok(ApiResponse.success("Cancelled successfully", cancelled));
    }

    @GetMapping("/{id}/invoice")
    @Operation(summary = "Get invoice for printing")
    public ResponseEntity<ApiResponse<BillDTO>> getInvoice(@PathVariable Long id) {
        BillDTO invoice = billService.getBillById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", invoice));
    }
}
