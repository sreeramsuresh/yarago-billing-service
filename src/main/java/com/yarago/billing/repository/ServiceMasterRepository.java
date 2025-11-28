package com.yarago.billing.repository;

import com.yarago.billing.entity.ServiceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceMasterRepository extends JpaRepository<ServiceMaster, Long> {

    Optional<ServiceMaster> findByServiceCode(String serviceCode);

    List<ServiceMaster> findByCategory(String category);

    List<ServiceMaster> findByIsActive(Boolean isActive);

    List<ServiceMaster> findByCategoryAndIsActive(String category, Boolean isActive);

    boolean existsByServiceCode(String serviceCode);
}
