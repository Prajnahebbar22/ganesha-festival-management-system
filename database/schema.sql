CREATE TABLE donations (
    donation_id SERIAL PRIMARY KEY,
    donor_name VARCHAR(100) NOT NULL,
    amount NUMERIC(12,2) NOT NULL,
    donation_type VARCHAR(20) NOT NULL,
    payment_mode VARCHAR(20) NOT NULL,
    donation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    festival_year INT NOT NULL,
    remarks VARCHAR(255)
);


CREATE TABLE expenses (
    expense_id SERIAL PRIMARY KEY,
    expense_name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    amount NUMERIC(12,2) NOT NULL,
    expense_date DATE NOT NULL DEFAULT CURRENT_DATE,
    festival_year INT NOT NULL,
    remarks VARCHAR(255)
);