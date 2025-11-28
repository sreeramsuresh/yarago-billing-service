-- Bills Table
CREATE TABLE bills (
    id BIGSERIAL PRIMARY KEY,
    bill_number VARCHAR(50) UNIQUE NOT NULL,
    patient_id BIGINT NOT NULL,
    patient_uhid VARCHAR(50) NOT NULL,
    patient_name VARCHAR(200) NOT NULL,
    appointment_id BIGINT,
    consultation_id BIGINT,

    bill_date DATE NOT NULL,
    due_date DATE,

    -- Amounts
    subtotal DECIMAL(10,2) NOT NULL,
    discount_percent DECIMAL(5,2),
    discount_amount DECIMAL(10,2),
    tax_percent DECIMAL(5,2),
    tax_amount DECIMAL(10,2),
    total_amount DECIMAL(10,2) NOT NULL,
    paid_amount DECIMAL(10,2) DEFAULT 0,
    balance_amount DECIMAL(10,2),

    payment_status VARCHAR(30) NOT NULL,
    bill_type VARCHAR(30) NOT NULL,
    notes TEXT,
    created_by VARCHAR(100),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Bill Items Table
CREATE TABLE bill_items (
    id BIGSERIAL PRIMARY KEY,
    bill_id BIGINT NOT NULL REFERENCES bills(id) ON DELETE CASCADE,

    item_type VARCHAR(50) NOT NULL,
    item_code VARCHAR(50),
    item_name VARCHAR(200) NOT NULL,
    description TEXT,

    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    discount_percent DECIMAL(5,2) DEFAULT 0,
    discount_amount DECIMAL(10,2) DEFAULT 0,
    tax_percent DECIMAL(5,2) DEFAULT 0,
    tax_amount DECIMAL(10,2) DEFAULT 0,
    total_price DECIMAL(10,2) NOT NULL,

    hsn_code VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Payments Table
CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    payment_number VARCHAR(50) UNIQUE NOT NULL,
    bill_id BIGINT NOT NULL REFERENCES bills(id) ON DELETE RESTRICT,
    patient_id BIGINT NOT NULL,

    amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(30) NOT NULL,
    payment_date TIMESTAMP NOT NULL,
    transaction_id VARCHAR(100),
    reference_number VARCHAR(100),
    status VARCHAR(30) NOT NULL,

    received_by VARCHAR(100),
    notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Service Master Table
CREATE TABLE service_master (
    id BIGSERIAL PRIMARY KEY,
    service_code VARCHAR(50) UNIQUE NOT NULL,
    service_name VARCHAR(200) NOT NULL,
    description TEXT,
    category VARCHAR(50) NOT NULL,

    base_price DECIMAL(10,2) NOT NULL,
    tax_percent DECIMAL(5,2) DEFAULT 0,
    hsn_code VARCHAR(20),

    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for Bills
CREATE INDEX idx_bill_number ON bills(bill_number);
CREATE INDEX idx_bill_patient ON bills(patient_uhid);
CREATE INDEX idx_bill_date ON bills(bill_date);
CREATE INDEX idx_payment_status ON bills(payment_status);

-- Indexes for Bill Items
CREATE INDEX idx_bill_item_bill ON bill_items(bill_id);
CREATE INDEX idx_bill_item_type ON bill_items(item_type);
CREATE INDEX idx_bill_item_code ON bill_items(item_code);

-- Indexes for Payments
CREATE INDEX idx_payment_number ON payments(payment_number);
CREATE INDEX idx_payment_bill ON payments(bill_id);
CREATE INDEX idx_payment_patient ON payments(patient_id);
CREATE INDEX idx_payment_date ON payments(payment_date);
CREATE INDEX idx_payments_status ON payments(status);

-- Indexes for Service Master
CREATE INDEX idx_service_code ON service_master(service_code);
CREATE INDEX idx_service_category ON service_master(category);
CREATE INDEX idx_service_active ON service_master(is_active);

-- Comments
COMMENT ON TABLE bills IS 'Stores billing and invoice information';
COMMENT ON TABLE bill_items IS 'Stores individual line items in bills';
COMMENT ON TABLE payments IS 'Stores payment transactions';
COMMENT ON TABLE service_master IS 'Stores service catalog with pricing';
