package com.yarago.billing.service;

import com.yarago.billing.dto.ServiceMasterDTO;
import com.yarago.billing.entity.ServiceMaster;
import com.yarago.billing.repository.ServiceMasterRepository;
import com.yarago.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceMasterService {

    private final ServiceMasterRepository serviceMasterRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ServiceMasterDTO createService(ServiceMasterDTO dto) {
        log.info("Creating new service: {}", dto.getServiceCode());

        ServiceMaster service = modelMapper.map(dto, ServiceMaster.class);
        ServiceMaster saved = serviceMasterRepository.save(service);

        log.info("Created service: {}", saved.getServiceCode());
        return modelMapper.map(saved, ServiceMasterDTO.class);
    }

    @Transactional(readOnly = true)
    public ServiceMasterDTO getServiceById(Long id) {
        ServiceMaster service = serviceMasterRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));
        return modelMapper.map(service, ServiceMasterDTO.class);
    }

    @Transactional(readOnly = true)
    public List<ServiceMasterDTO> getAllServices() {
        return serviceMasterRepository.findAll()
            .stream()
            .map(service -> modelMapper.map(service, ServiceMasterDTO.class))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ServiceMasterDTO> getServicesByCategory(String category) {
        return serviceMasterRepository.findByCategoryAndIsActive(category, true)
            .stream()
            .map(service -> modelMapper.map(service, ServiceMasterDTO.class))
            .collect(Collectors.toList());
    }

    @Transactional
    public ServiceMasterDTO updateService(Long id, ServiceMasterDTO dto) {
        log.info("Updating service: {}", id);

        ServiceMaster service = serviceMasterRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));

        service.setServiceName(dto.getServiceName());
        service.setDescription(dto.getDescription());
        service.setBasePrice(dto.getBasePrice());
        service.setTaxPercent(dto.getTaxPercent());
        service.setHsnCode(dto.getHsnCode());
        service.setIsActive(dto.getIsActive());

        ServiceMaster updated = serviceMasterRepository.save(service);
        log.info("Updated service: {}", updated.getServiceCode());

        return modelMapper.map(updated, ServiceMasterDTO.class);
    }
}
