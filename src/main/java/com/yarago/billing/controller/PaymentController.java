package com.yarago.billing.controller;

import com.yarago.billing.dto.PaymentDTO;
import com.yarago.billing.service.PaymentService;
import com.yarago.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "Payment management APIs")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Record payment")
    public ResponseEntity<ApiResponse<PaymentDTO>> recordPayment(@Valid @RequestBody PaymentDTO dto) {
        PaymentDTO created = paymentService.recordPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Recorded successfully", created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment by ID")
    public ResponseEntity<ApiResponse<PaymentDTO>> getPaymentById(@PathVariable Long id) {
        PaymentDTO payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", payment));
    }

    @GetMapping("/bill/{billId}")
    @Operation(summary = "Get payments for a bill")
    public ResponseEntity<ApiResponse<List<PaymentDTO>>> getPaymentsByBillId(@PathVariable Long billId) {
        List<PaymentDTO> payments = paymentService.getPaymentsByBillId(billId);
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", payments));
    }
}
