package com.yarago.billing.service;

import com.yarago.billing.dto.BillDTO;
import com.yarago.billing.dto.BillItemDTO;
import com.yarago.billing.entity.Bill;
import com.yarago.billing.entity.BillItem;
import com.yarago.billing.repository.BillRepository;
import com.yarago.common.exception.ResourceNotFoundException;
import com.yarago.common.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final NumberGeneratorService numberGeneratorService;
    private final ModelMapper modelMapper;

    @Transactional
    public BillDTO createBill(BillDTO dto) {
        log.info("Creating new bill for patient: {}", dto.getPatientUhid());

        Bill bill = modelMapper.map(dto, Bill.class);
        bill.setBillNumber(numberGeneratorService.generateBillNumber());

        if (bill.getBillDate() == null) {
            bill.setBillDate(LocalDate.now());
        }

        if (bill.getPaymentStatus() == null) {
            bill.setPaymentStatus(Bill.PaymentStatus.PENDING);
        }

        // Map and add items
        if (dto.getItems() != null && !dto.getItems().isEmpty()) {
            for (BillItemDTO itemDto : dto.getItems()) {
                BillItem item = modelMapper.map(itemDto, BillItem.class);
                item.calculateTotal();
                bill.addItem(item);
            }
        }

        // Calculate totals
        bill.calculateTotals();

        Bill saved = billRepository.save(bill);
        log.info("Created bill: {}", saved.getBillNumber());

        return mapToDTO(saved);
    }

    @Transactional(readOnly = true)
    public BillDTO getBillById(Long id) {
        Bill bill = billRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
        return mapToDTO(bill);
    }

    @Transactional(readOnly = true)
    public BillDTO getBillByNumber(String billNumber) {
        Bill bill = billRepository.findByBillNumber(billNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Bill not found: " + billNumber));
        return mapToDTO(bill);
    }

    @Transactional(readOnly = true)
    public List<BillDTO> getPatientBills(Long patientId) {
        return billRepository.findByPatientIdOrderByBillDateDesc(patientId)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BillDTO> getBillsByDateRange(LocalDate startDate, LocalDate endDate) {
        return billRepository.findByBillDateBetween(startDate, endDate)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public BillDTO updateBill(Long id, BillDTO dto) {
        log.info("Updating bill: {}", id);

        Bill bill = billRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));

        if (bill.getPaymentStatus() == Bill.PaymentStatus.PAID) {
            throw new ValidationException("Cannot update paid bill");
        }

        // Update fields
        bill.setDiscountPercent(dto.getDiscountPercent());
        bill.setDiscountAmount(dto.getDiscountAmount());
        bill.setNotes(dto.getNotes());

        bill.calculateTotals();

        Bill updated = billRepository.save(bill);
        log.info("Updated bill: {}", updated.getBillNumber());

        return mapToDTO(updated);
    }

    @Transactional
    public BillDTO addItem(Long billId, BillItemDTO itemDto) {
        log.info("Adding item to bill: {}", billId);

        Bill bill = billRepository.findById(billId)
            .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + billId));

        if (bill.getPaymentStatus() == Bill.PaymentStatus.PAID) {
            throw new ValidationException("Cannot modify paid bill");
        }

        BillItem item = modelMapper.map(itemDto, BillItem.class);
        item.calculateTotal();
        bill.addItem(item);
        bill.calculateTotals();

        Bill updated = billRepository.save(bill);
        return mapToDTO(updated);
    }

    @Transactional
    public BillDTO cancelBill(Long id) {
        log.info("Cancelling bill: {}", id);

        Bill bill = billRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));

        if (bill.getPaymentStatus() == Bill.PaymentStatus.PAID) {
            throw new ValidationException("Cannot cancel paid bill");
        }

        bill.setPaymentStatus(Bill.PaymentStatus.CANCELLED);
        Bill updated = billRepository.save(bill);

        log.info("Cancelled bill: {}", updated.getBillNumber());
        return mapToDTO(updated);
    }

    @Transactional
    public void recordPayment(Long billId, BigDecimal amount) {
        Bill bill = billRepository.findById(billId)
            .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + billId));

        BigDecimal currentPaid = bill.getPaidAmount() != null ? bill.getPaidAmount() : BigDecimal.ZERO;
        bill.setPaidAmount(currentPaid.add(amount));
        bill.calculateTotals();

        billRepository.save(bill);
    }

    private BillDTO mapToDTO(Bill bill) {
        BillDTO dto = modelMapper.map(bill, BillDTO.class);

        if (bill.getItems() != null) {
            dto.setItems(bill.getItems().stream()
                .map(item -> modelMapper.map(item, BillItemDTO.class))
                .collect(Collectors.toList()));
        }

        return dto;
    }
}
