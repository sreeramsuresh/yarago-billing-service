package com.yarago.billing.client;

import com.yarago.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", path = "/patient-service/api/v1/patients")
public interface PatientServiceClient {

    @GetMapping("/{id}")
    ApiResponse<Object> getPatientById(@PathVariable Long id);

    @GetMapping("/uhid/{uhid}")
    ApiResponse<Object> getPatientByUhid(@PathVariable String uhid);
}
