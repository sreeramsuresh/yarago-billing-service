-- Seed Bills and Payments (50+ records)

-- Insert Bills
INSERT INTO bills (bill_number, patient_id, patient_uhid, patient_name, appointment_id, consultation_id,
    bill_date, subtotal, discount_percent, discount_amount, tax_percent, tax_amount, total_amount,
    paid_amount, balance_amount, payment_status, bill_type, created_by, created_at, updated_at) VALUES
('BIL-20250115-0001', 1, 'YRG-2025-0001', 'Rajesh Kumar', 1, 1, '2025-01-15', 2500.00, 0, 0, 15.30, 382.50, 2882.50, 2882.50, 0, 'PAID', 'CONSULTATION', 'admin', '2025-01-15 12:00:00', '2025-01-15 12:00:00'),
('BIL-20250116-0002', 2, 'YRG-2025-0002', 'Priya Sharma', 2, 2, '2025-01-16', 3300.00, 0, 0, 15.62, 515.46, 3815.46, 3815.46, 0, 'PAID', 'MIXED', 'admin', '2025-01-16 13:00:00', '2025-01-16 13:00:00'),
('BIL-20250117-0003', 3, 'YRG-2025-0003', 'Amit Patel', 3, 3, '2025-01-17', 5500.00, 0, 0, 17.09, 940.00, 6440.00, 6440.00, 0, 'PAID', 'OPTICAL', 'admin', '2025-01-17 11:00:00', '2025-01-17 11:00:00'),
('BIL-20250118-0004', 4, 'YRG-2025-0004', 'Sunita Reddy', 4, 4, '2025-01-18', 2800.00, 10, 280.00, 15.30, 385.56, 2905.56, 2905.56, 0, 'PAID', 'CONSULTATION', 'admin', '2025-01-18 16:00:00', '2025-01-18 16:00:00'),
('BIL-20250119-0005', 5, 'YRG-2025-0005', 'Vikram Singh', 5, 5, '2025-01-19', 2000.00, 0, 0, 14.40, 288.00, 2288.00, 2288.00, 0, 'PAID', 'OPTICAL', 'admin', '2025-01-19 12:00:00', '2025-01-19 12:00:00'),
('BIL-20250120-0006', 6, 'YRG-2025-0006', 'Meera Nair', 6, 6, '2025-01-20', 4800.00, 0, 0, 16.98, 814.70, 5614.70, 5614.70, 0, 'PAID', 'MIXED', 'admin', '2025-01-20 14:00:00', '2025-01-20 14:00:00'),
('BIL-20250121-0007', 7, 'YRG-2025-0007', 'Arjun Desai', 7, 7, '2025-01-21', 500.00, 0, 0, 18.00, 90.00, 590.00, 590.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-01-21 11:00:00', '2025-01-21 11:00:00'),
('BIL-20250122-0008', 8, 'YRG-2025-0008', 'Kavita Joshi', 8, 8, '2025-01-22', 6000.00, 5, 300.00, 17.09, 974.13, 6674.13, 6674.13, 0, 'PAID', 'OPTICAL', 'admin', '2025-01-22 15:00:00', '2025-01-22 15:00:00'),
('BIL-20250123-0009', 9, 'YRG-2025-0009', 'Rahul Verma', 9, 9, '2025-01-23', 2200.00, 0, 0, 14.73, 324.00, 2524.00, 2524.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-01-23 12:00:00', '2025-01-23 12:00:00'),
('BIL-20250124-0010', 10, 'YRG-2025-0010', 'Deepa Menon', 10, 10, '2025-01-24', 2700.00, 0, 0, 16.07, 434.00, 3134.00, 3134.00, 0, 'PAID', 'MIXED', 'admin', '2025-01-24 17:00:00', '2025-01-24 17:00:00'),
('BIL-20250125-0011', 11, 'YRG-2025-0011', 'Sanjay Gupta', 11, 11, '2025-01-25', 2800.00, 0, 0, 14.73, 412.32, 3212.32, 3212.32, 0, 'PAID', 'OPTICAL', 'admin', '2025-01-25 10:00:00', '2025-01-25 10:00:00'),
('BIL-20250126-0012', 12, 'YRG-2025-0012', 'Anjali Roy', 12, 12, '2025-01-26', 7000.00, 0, 0, 17.09, 1196.00, 8196.00, 8196.00, 0, 'PAID', 'OPTICAL', 'admin', '2025-01-26 14:00:00', '2025-01-26 14:00:00'),
('BIL-20250127-0013', 13, 'YRG-2025-0013', 'Ramesh Iyer', 13, 13, '2025-01-27', 5500.00, 10, 550.00, 17.09, 845.95, 5795.95, 5795.95, 0, 'PAID', 'OPTICAL', 'admin', '2025-01-27 12:00:00', '2025-01-27 12:00:00'),
('BIL-20250128-0014', 14, 'YRG-2025-0014', 'Lakshmi Rao', 14, 14, '2025-01-28', 3300.00, 0, 0, 16.07, 530.31, 3830.31, 3830.31, 0, 'PAID', 'MIXED', 'admin', '2025-01-28 16:00:00', '2025-01-28 16:00:00'),
('BIL-20250129-0015', 15, 'YRG-2025-0015', 'Karthik Nair', 15, 15, '2025-01-29', 2800.00, 0, 0, 14.73, 412.32, 3212.32, 3212.32, 0, 'PAID', 'OPTICAL', 'admin', '2025-01-29 11:00:00', '2025-01-29 11:00:00'),
('BIL-20250130-0016', 16, 'YRG-2025-0016', 'Divya Krishnan', 16, 16, '2025-01-30', 6500.00, 5, 325.00, 17.09, 1055.35, 7230.35, 7230.35, 0, 'PAID', 'OPTICAL', 'admin', '2025-01-30 13:00:00', '2025-01-30 13:00:00'),
('BIL-20250131-0017', 17, 'YRG-2025-0017', 'Manoj Kumar', 17, 17, '2025-01-31', 500.00, 0, 0, 18.00, 90.00, 590.00, 590.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-01-31 11:00:00', '2025-01-31 11:00:00'),
('BIL-20250201-0018', 18, 'YRG-2025-0018', 'Neha Sharma', 18, 18, '2025-02-01', 4300.00, 0, 0, 16.98, 729.96, 5029.96, 5029.96, 0, 'PAID', 'MIXED', 'admin', '2025-02-01 17:00:00', '2025-02-01 17:00:00'),
('BIL-20250202-0019', 19, 'YRG-2025-0019', 'Suresh Reddy', 19, 19, '2025-02-02', 2000.00, 0, 0, 14.40, 288.00, 2288.00, 2288.00, 0, 'PAID', 'OPTICAL', 'admin', '2025-02-02 10:00:00', '2025-02-02 10:00:00'),
('BIL-20250203-0020', 20, 'YRG-2025-0020', 'Pooja Mehta', 20, 20, '2025-02-03', 8000.00, 0, 0, 17.09, 1367.20, 9367.20, 9367.20, 0, 'PAID', 'OPTICAL', 'admin', '2025-02-03 14:00:00', '2025-02-03 14:00:00'),
('BIL-20250204-0021', 1, 'YRG-2025-0001', 'Rajesh Kumar', 21, 21, '2025-02-04', 300.00, 0, 0, 18.00, 54.00, 354.00, 354.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-04 11:00:00', '2025-02-04 11:00:00'),
('BIL-20250205-0022', 2, 'YRG-2025-0002', 'Priya Sharma', 22, 22, '2025-02-05', 300.00, 0, 0, 18.00, 54.00, 354.00, 354.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-05 14:00:00', '2025-02-05 14:00:00'),
('BIL-20250206-0023', 3, 'YRG-2025-0003', 'Amit Patel', 23, 23, '2025-02-06', 300.00, 0, 0, 18.00, 54.00, 354.00, 354.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-06 11:00:00', '2025-02-06 11:00:00'),
('BIL-20250207-0024', 4, 'YRG-2025-0004', 'Sunita Reddy', 24, 24, '2025-02-07', 300.00, 0, 0, 18.00, 54.00, 354.00, 354.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-07 16:00:00', '2025-02-07 16:00:00'),
('BIL-20250208-0025', 5, 'YRG-2025-0005', 'Vikram Singh', 25, 25, '2025-02-08', 300.00, 0, 0, 18.00, 54.00, 354.00, 354.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-08 11:00:00', '2025-02-08 11:00:00'),
('BIL-20250209-0026', 6, 'YRG-2025-0006', 'Meera Nair', 26, 26, '2025-02-09', 1800.00, 0, 0, 18.00, 324.00, 2124.00, 2124.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-09 13:00:00', '2025-02-09 13:00:00'),
('BIL-20250210-0027', 7, 'YRG-2025-0007', 'Arjun Desai', 27, 27, '2025-02-10', 300.00, 0, 0, 18.00, 54.00, 354.00, 354.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-10 10:00:00', '2025-02-10 10:00:00'),
('BIL-20250211-0028', 8, 'YRG-2025-0008', 'Kavita Joshi', 28, 28, '2025-02-11', 1500.00, 0, 0, 18.00, 270.00, 1770.00, 1770.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-11 15:00:00', '2025-02-11 15:00:00'),
('BIL-20250212-0029', 9, 'YRG-2025-0009', 'Rahul Verma', 29, 29, '2025-02-12', 300.00, 0, 0, 18.00, 54.00, 354.00, 354.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-12 11:00:00', '2025-02-12 11:00:00'),
('BIL-20250213-0030', 10, 'YRG-2025-0010', 'Deepa Menon', 30, 30, '2025-02-13', 1500.00, 0, 0, 18.00, 270.00, 1770.00, 1770.00, 0, 'PAID', 'CONSULTATION', 'admin', '2025-02-13 16:00:00', '2025-02-13 16:00:00');

-- Insert Bill Items for the above bills

-- BIL-20250115-0001 (Registration + Consultation + Test)
INSERT INTO bill_items (bill_id, item_type, item_code, item_name, quantity, unit_price, discount_percent, discount_amount, tax_percent, tax_amount, total_price, hsn_code) VALUES
((SELECT id FROM bills WHERE bill_number = 'BIL-20250115-0001'), 'REGISTRATION_FEE', 'REG-NEW', 'New Patient Registration', 1, 100.00, 0, 0, 18.00, 18.00, 118.00, '999311'),
((SELECT id FROM bills WHERE bill_number = 'BIL-20250115-0001'), 'CONSULTATION_FEE', 'CONS-GEN', 'General Consultation', 1, 500.00, 0, 0, 18.00, 90.00, 590.00, '999311'),
((SELECT id FROM bills WHERE bill_number = 'BIL-20250115-0001'), 'TEST', 'TEST-REF', 'Refraction Test', 1, 300.00, 0, 0, 18.00, 54.00, 354.00, '999311'),
((SELECT id FROM bills WHERE bill_number = 'BIL-20250115-0001'), 'TEST', 'TEST-IOP', 'IOP Measurement', 1, 250.00, 0, 0, 18.00, 45.00, 295.00, '999311'),
((SELECT id FROM bills WHERE bill_number = 'BIL-20250115-0001'), 'TEST', 'TEST-OCT', 'OCT Scan', 1, 1500.00, 0, 0, 18.00, 270.00, 1770.00, '999311');

-- BIL-20250116-0002 (Consultation + Glasses)
INSERT INTO bill_items (bill_id, item_type, item_code, item_name, quantity, unit_price, discount_percent, discount_amount, tax_percent, tax_amount, total_price, hsn_code) VALUES
((SELECT id FROM bills WHERE bill_number = 'BIL-20250116-0002'), 'CONSULTATION_FEE', 'CONS-GEN', 'General Consultation', 1, 500.00, 0, 0, 18.00, 90.00, 590.00, '999311'),
((SELECT id FROM bills WHERE bill_number = 'BIL-20250116-0002'), 'LENS', 'OPT-SV', 'Single Vision Glasses', 1, 2000.00, 0, 0, 12.00, 240.00, 2240.00, '900390'),
((SELECT id FROM bills WHERE bill_number = 'BIL-20250116-0002'), 'LENS', 'OPT-AG', 'Anti-Glare Coating', 1, 800.00, 0, 0, 12.00, 96.00, 896.00, '900390');

-- BIL-20250117-0003 (Progressive Glasses with Premium Frame)
INSERT INTO bill_items (bill_id, item_type, item_code, item_name, quantity, unit_price, discount_percent, discount_amount, tax_percent, tax_amount, total_price, hsn_code) VALUES
((SELECT id FROM bills WHERE bill_number = 'BIL-20250117-0003'), 'LENS', 'OPT-PR', 'Progressive Glasses', 1, 5000.00, 0, 0, 12.00, 600.00, 5600.00, '900390'),
((SELECT id FROM bills WHERE bill_number = 'BIL-20250117-0003'), 'FRAME', 'OPT-FR', 'Standard Frame', 1, 1500.00, 0, 0, 12.00, 180.00, 1680.00, '900390');

-- More bill items for remaining bills (abbreviated for space)
INSERT INTO bill_items (bill_id, item_type, item_code, item_name, quantity, unit_price, discount_percent, discount_amount, tax_percent, tax_amount, total_price, hsn_code)
SELECT id, 'CONSULTATION_FEE', 'CONS-GEN', 'General Consultation', 1, 500.00, 0, 0, 18.00, 90.00, 590.00, '999311'
FROM bills WHERE bill_number IN ('BIL-20250121-0007', 'BIL-20250131-0017');

-- Follow-up consultations
INSERT INTO bill_items (bill_id, item_type, item_code, item_name, quantity, unit_price, discount_percent, discount_amount, tax_percent, tax_amount, total_price, hsn_code)
SELECT id, 'CONSULTATION_FEE', 'CONS-FOL', 'Follow-up Consultation', 1, 300.00, 0, 0, 18.00, 54.00, 354.00, '999311'
FROM bills WHERE bill_number IN ('BIL-20250204-0021', 'BIL-20250205-0022', 'BIL-20250206-0023', 'BIL-20250207-0024',
'BIL-20250208-0025', 'BIL-20250210-0027', 'BIL-20250212-0029');

-- Insert Payments for all bills
INSERT INTO payments (payment_number, bill_id, patient_id, amount, payment_method, payment_date, status, received_by)
SELECT
    'PAY-' || TO_CHAR(bill_date, 'YYYYMMDD') || '-' || LPAD(ROW_NUMBER() OVER (PARTITION BY bill_date ORDER BY id)::TEXT, 4, '0'),
    id,
    patient_id,
    total_amount,
    CASE (ROW_NUMBER() OVER (ORDER BY id)) % 4
        WHEN 0 THEN 'CASH'
        WHEN 1 THEN 'UPI'
        WHEN 2 THEN 'CARD'
        ELSE 'NETBANKING'
    END,
    created_at,
    'SUCCESS',
    'Reception Staff'
FROM bills
WHERE payment_status = 'PAID';
