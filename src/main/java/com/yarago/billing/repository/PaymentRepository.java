package com.yarago.billing.repository;

import com.yarago.billing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentNumber(String paymentNumber);

    List<Payment> findByBillId(Long billId);

    List<Payment> findByPatientId(Long patientId);

    List<Payment> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Payment> findByStatus(Payment.PaymentStatus status);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE " +
           "CAST(p.paymentDate AS LocalDate) = CURRENT_DATE AND p.status = 'SUCCESS'")
    BigDecimal getTodayCollection();

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE " +
           "p.paymentDate BETWEEN :startDate AND :endDate AND p.status = 'SUCCESS'")
    BigDecimal getCollectionByDateRange(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );

    boolean existsByPaymentNumber(String paymentNumber);
}
