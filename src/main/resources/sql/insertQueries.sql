use david_bankdb;
-- Sample data for customers table
INSERT INTO customers (first_name, last_name, address, phone_number, email)
VALUES
  ('John', 'Doe', '123 Main St', '123-456-7890', 'john.doe@example.com'),
  ('Jane', 'Smith', '456 Elm St', '987-654-3210', 'jane.smith@example.com'),
  ('Michael', 'Johnson', '789 Oak St', '555-555-5555', 'michael.johnson@example.com');

-- Sample data for branches table
INSERT INTO branches (branch_name, location)
VALUES
  ('Main Branch', 'New York'),
  ('Downtown Branch', 'Los Angeles'),
  ('Uptown Branch', 'Chicago');

-- Sample data for accounts table
INSERT INTO accounts (account_type, balance, open_date, last_transaction_date, branch_id)
VALUES
  ('Savings', 5000.00, '2022-01-01', '2023-05-30', 1),
  ('Checking', 1000.00, '2022-01-01', '2023-05-30', 2),
  ('Savings', 2000.00, '2022-01-01', '2023-05-30', 3);

-- Sample data for credit_cards table
INSERT INTO credit_cards (customer_id, credit_card_number, expiry_date, credit_limit, outstanding_balance)
VALUES
  (1, '1234567890123456', '2025-12-31', 5000.00, 1500.00),
  (2, '9876543210987654', '2024-10-31', 3000.00, 0.00);

-- Sample data for debit_cards table
INSERT INTO debit_cards (customer_id, debit_card_number, expiry_date)
VALUES
  (1, '1111222233334444', '2026-06-30'),
  (2, '5555666677778888', '2025-04-30'),
  (3, '9999000011112222', '2027-09-30');

-- Sample data for loans table
INSERT INTO loans (customer_id, loan_type, loan_amount, interest_rate, start_date, end_date)
VALUES
  (1, 'Home Loan', 200000.00, 4.5, '2020-02-01', '2040-02-01'),
  (3, 'Car Loan', 30000.00, 3.25, '2021-05-01', '2026-05-01');

-- Sample data for transaction_type table
INSERT INTO transaction_type (transaction_type_id, type_name)
VALUES
  (1, 'Deposit'),
  (2, 'Withdrawal'),
  (3, 'Transfer');

-- Sample data for transactions table
INSERT INTO transactions (transaction_type_id, account_id, amount, transaction_date, source_account_id, destination_account_id)
VALUES
  (1, 1, 1000.00, '2023-06-01', 1, 1),
  (2, 2, 500.00, '2023-06-02', 2, 2),
  (3, 3, 200.00, '2023-06-03', 1, 2),
  (1, 1, 500.00, '2023-06-04', 1, 1),
  (2, 2, 200.00, '2023-06-05', 2, 2),
  (3, 3, 100.00, '2023-06-06', 2, 1);

-- Sample data for account_holders table
INSERT INTO account_holders (account_holder_id, account_id, customer_id)
VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 3, 3);

-- Sample data for branch_employees table
INSERT INTO branch_employees (branch_id, first_name, last_name, position)
VALUES
  (1, 'Mark', 'Davis', 'Manager'),
  (2, 'Emily', 'Wilson', 'Teller'),
  (3, 'James', 'Thompson', 'Teller');
