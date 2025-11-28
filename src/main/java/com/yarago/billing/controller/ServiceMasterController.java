package com.yarago.billing.controller;

import com.yarago.billing.dto.ServiceMasterDTO;
import com.yarago.billing.service.ServiceMasterService;
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
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
@Tag(name = "Service Master", description = "Service catalog management APIs")
public class ServiceMasterController {

    private final ServiceMasterService serviceMasterService;

    @PostMapping
    @Operation(summary = "Create new service")
    public ResponseEntity<ApiResponse<ServiceMasterDTO>> createService(@Valid @RequestBody ServiceMasterDTO dto) {
        ServiceMasterDTO created = serviceMasterService.createService(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Created successfully", created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get service by ID")
    public ResponseEntity<ApiResponse<ServiceMasterDTO>> getServiceById(@PathVariable Long id) {
        ServiceMasterDTO service = serviceMasterService.getServiceById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", service));
    }

    @GetMapping
    @Operation(summary = "Get all services")
    public ResponseEntity<ApiResponse<List<ServiceMasterDTO>>> getAllServices() {
        List<ServiceMasterDTO> services = serviceMasterService.getAllServices();
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", services));
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get services by category")
    public ResponseEntity<ApiResponse<List<ServiceMasterDTO>>> getServicesByCategory(@PathVariable String category) {
        List<ServiceMasterDTO> services = serviceMasterService.getServicesByCategory(category);
        return ResponseEntity.ok(ApiResponse.success("Retrieved successfully", services));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update service")
    public ResponseEntity<ApiResponse<ServiceMasterDTO>> updateService(
            @PathVariable Long id,
            @Valid @RequestBody ServiceMasterDTO dto) {
        ServiceMasterDTO updated = serviceMasterService.updateService(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Updated successfully", updated));
    }
}
