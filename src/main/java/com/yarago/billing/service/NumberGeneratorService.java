package com.yarago.billing.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NumberGeneratorService {

    private final AtomicInteger billCounter = new AtomicInteger(0);
    private final AtomicInteger paymentCounter = new AtomicInteger(0);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public String generateBillNumber() {
        String dateStr = LocalDate.now().format(DATE_FORMATTER);
        int sequence = billCounter.incrementAndGet();
        return String.format("BIL-%s-%04d", dateStr, sequence);
    }

    public String generatePaymentNumber() {
        String dateStr = LocalDate.now().format(DATE_FORMATTER);
        int sequence = paymentCounter.incrementAndGet();
        return String.format("PAY-%s-%04d", dateStr, sequence);
    }
}
