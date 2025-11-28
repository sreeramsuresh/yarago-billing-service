package com.yarago.billing.client;

import com.yarago.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consultation-service", path = "/consultation-service/api/v1/consultations")
public interface ConsultationServiceClient {

    @GetMapping("/{id}")
    ApiResponse<Object> getConsultationById(@PathVariable Long id);
}
