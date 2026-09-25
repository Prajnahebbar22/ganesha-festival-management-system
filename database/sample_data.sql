INSERT INTO donations
(donor_name, amount, donation_type, payment_mode, festival_year, remarks)
VALUES
('Ramesh', 500, 'FESTIVAL', 'UPI', 2026, 'Main donation'),
('Suresh', 150, 'FESTIVAL', 'CASH', 2026, 'Regular donation'),
('Anitha', 1000, 'FESTIVAL', 'BANK', 2026, 'Family contribution'),
('Meena', 200, 'FOOD', 'UPI', 2026, 'Food donation'),
('Kiran', 300, 'FOOD', 'CASH', 2026, 'Food arrangement'),
('Deepa', 100, 'FOOD', 'UPI', 2026, 'Food donation');

INSERT INTO expenses
(expense_name, category, amount, festival_year, remarks)
VALUES
('Decoration', 'Decoration', 15000, 2026, 'Stage and pandal decoration'),
('Food Materials', 'Food', 25000, 2026, 'Groceries and ingredients'),
('Flowers', 'Pooja', 5000, 2026, 'Flowers for festival'),
('Sound System', 'Equipment', 8000, 2026, 'Sound system rental'),
('Electricity', 'Utilities', 3000, 2026, 'Temporary electrical connection');