SELECT *
FROM donations
WHERE festival_year = 2026;


SELECT donor_name, amount
FROM donations
WHERE amount > 200
AND festival_year = 2026
ORDER BY amount DESC;



SELECT SUM(amount) AS total_festival_donations
FROM donations
WHERE donation_type = 'FESTIVAL'
AND festival_year = 2026;




SELECT SUM(amount) AS total_food_donations
FROM donations
WHERE donation_type = 'FOOD'
AND festival_year = 2026;



SELECT SUM(amount) AS total_donations
FROM donations
WHERE festival_year = 2026;


SELECT SUM(amount) AS total_expenses
FROM expenses
WHERE festival_year = 2026;


SELECT
    (SELECT SUM(amount)
     FROM donations
     WHERE festival_year = 2026)
    -
    (SELECT SUM(amount)
     FROM expenses
     WHERE festival_year = 2026)
    AS remaining_amount;


SELECT *
FROM expenses
WHERE festival_year = 2026
ORDER BY amount DESC;