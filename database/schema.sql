CREATE TABLE donations (
    donation_id SERIAL PRIMARY KEY,
    donor_name VARCHAR(100) NOT NULL,
    amount NUMERIC(12,2) NOT NULL,
    donation_type VARCHAR(20) NOT NULL,
    payment_mode VARCHAR(20) NOT NULL,
    donation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    festival_year INT NOT NULL,
    remarks VARCHAR(255),

    CONSTRAINT donations_amount_positive
        CHECK (amount > 0),

    CONSTRAINT donations_type_valid
        CHECK (donation_type IN ('FESTIVAL', 'FOOD')),

    CONSTRAINT donations_payment_mode_valid
        CHECK (payment_mode IN ('CASH', 'UPI', 'BANK')),

    CONSTRAINT donations_festival_year_valid
        CHECK (festival_year BETWEEN 2000 AND 2100)
);


CREATE TABLE expenses (
    expense_id SERIAL PRIMARY KEY,
    expense_name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    amount NUMERIC(12,2) NOT NULL,
    expense_date DATE NOT NULL DEFAULT CURRENT_DATE,
    festival_year INT NOT NULL,
    remarks VARCHAR(255),

    CONSTRAINT expenses_amount_positive
        CHECK (amount > 0),

    CONSTRAINT expenses_festival_year_valid
        CHECK (festival_year BETWEEN 2000 AND 2100)
);