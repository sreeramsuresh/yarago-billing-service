package com.yarago.billing.service;

import com.yarago.billing.dto.PaymentDTO;
import com.yarago.billing.entity.Bill;
import com.yarago.billing.entity.Payment;
import com.yarago.billing.repository.BillRepository;
import com.yarago.billing.repository.PaymentRepository;
import com.yarago.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BillRepository billRepository;
    private final NumberGeneratorService numberGeneratorService;
    private final BillService billService;
    private final ModelMapper modelMapper;

    @Transactional
    public PaymentDTO recordPayment(PaymentDTO dto) {
        log.info("Recording payment for bill ID: {}", dto.getBillId());

        Bill bill = billRepository.findById(dto.getBillId())
            .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + dto.getBillId()));

        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setPaymentNumber(numberGeneratorService.generatePaymentNumber());
        payment.setBill(bill);

        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDateTime.now());
        }

        if (payment.getStatus() == null) {
            payment.setStatus(Payment.PaymentStatus.SUCCESS);
        }

        Payment saved = paymentRepository.save(payment);

        // Update bill payment status
        billService.recordPayment(dto.getBillId(), dto.getAmount());

        log.info("Recorded payment: {}", saved.getPaymentNumber());
        return modelMapper.map(saved, PaymentDTO.class);
    }

    @Transactional(readOnly = true)
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        return modelMapper.map(payment, PaymentDTO.class);
    }

    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByBillId(Long billId) {
        return paymentRepository.findByBillId(billId)
            .stream()
            .map(payment -> modelMapper.map(payment, PaymentDTO.class))
            .collect(Collectors.toList());
    }
}
