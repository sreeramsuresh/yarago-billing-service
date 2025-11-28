package com.yarago.billing.repository;

import com.yarago.billing.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByBillNumber(String billNumber);

    List<Bill> findByPatientIdOrderByBillDateDesc(Long patientId);

    List<Bill> findByPatientUhidOrderByBillDateDesc(String patientUhid);

    List<Bill> findByPaymentStatus(Bill.PaymentStatus paymentStatus);

    List<Bill> findByBillDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT b FROM Bill b WHERE b.billDate = :date ORDER BY b.createdAt DESC")
    List<Bill> findByBillDate(@Param("date") LocalDate date);

    @Query("SELECT b FROM Bill b WHERE b.billDate BETWEEN :startDate AND :endDate " +
           "AND b.paymentStatus = :status")
    List<Bill> findByDateRangeAndStatus(
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate,
        @Param("status") Bill.PaymentStatus status
    );

    @Query("SELECT SUM(b.totalAmount) FROM Bill b WHERE b.billDate = :date " +
           "AND b.paymentStatus IN ('PAID', 'PARTIAL')")
    BigDecimal getDailyRevenue(@Param("date") LocalDate date);

    boolean existsByBillNumber(String billNumber);
}
