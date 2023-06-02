use bankdb;
-- Sample data for Customers table
INSERT INTO Customers (First_Name, Last_Name, Address, Phone_Number, Email)
VALUES
  ('John', 'Doe', '123 Main St', '123-456-7890', 'john.doe@example.com'),
  ('Jane', 'Smith', '456 Elm St', '987-654-3210', 'jane.smith@example.com'),
  ('Michael', 'Johnson', '789 Oak St', '555-555-5555', 'michael.johnson@example.com');

-- Sample data for Branches table
INSERT INTO Branches (Branch_Name, Location)
VALUES
  ('Main Branch', 'New York'),
  ('Downtown Branch', 'Los Angeles'),
  ('Uptown Branch', 'Chicago');

-- Sample data for Accounts table
INSERT INTO Accounts (Account_Type, Balance, Open_Date, Last_Transaction_Date, Branch_ID)
VALUES
  ('Savings', 5000.00, '2022-01-01', '2023-05-30', 1),
  ('Checking', 1000.00, '2022-01-01', '2023-05-30', 2),
  ('Savings', 2000.00, '2022-01-01', '2023-05-30', 3);

-- Sample data for Credit_Cards table
INSERT INTO Credit_Cards (Customer_ID, Credit_card_number, Expiry_Date, Credit_Limit, Outstanding_Balance)
VALUES
  (1, '1234567890123456', '2025-12-31', 5000.00, 1500.00),
  (2, '9876543210987654', '2024-10-31', 3000.00, 0.00);

-- Sample data for Debit_Cards table
INSERT INTO Debit_Cards (Customer_ID, Debit_card_number, Expiry_Date)
VALUES
  (1, '1111222233334444', '2026-06-30'),
  (2, '5555666677778888', '2025-04-30'),
  (3, '9999000011112222', '2027-09-30');

-- Sample data for Loans table
INSERT INTO Loans (Customer_ID, Loan_Type, Loan_Amount, Interest_Rate, Start_Date, End_Date)
VALUES
  (1, 'Home Loan', 200000.00, 4.5, '2020-02-01', '2040-02-01'),
  (3, 'Car Loan', 30000.00, 3.25, '2021-05-01', '2026-05-01');

-- Sample data for Transactions table
INSERT INTO Transactions (Account_ID, Transaction_Type, Amount, Transaction_Date)
VALUES
  (1, 'Deposit', 1000.00, '2023-05-30'),
  (2, 'Withdrawal', 200.00, '2023-05-29'),
  (3, 'Transfer', 200.00, '2023-05-28');

-- Sample data for Account_Holders table
INSERT INTO Account_Holders (account_holder_ID, Account_ID, Customer_ID)
VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 3, 3);

-- Sample data for Branch_Employees table
INSERT INTO Branch_Employees (Branch_ID, First_Name, Last_Name, Position)
VALUES
  (1, 'Mark', 'Davis', 'Manager'),
  (2, 'Emily', 'Wilson', 'Teller'),
  (3, 'James', 'Thompson', 'Teller');

-- Sample data for Deposits table
INSERT INTO Deposits (Transaction_ID, Amount, Deposit_Date)
VALUES
  (1, 1000.00, '2023-05-30');

-- Sample data for Withdrawals table
INSERT INTO Withdrawals (Transaction_ID, Amount, Withdrawal_Date)
VALUES
  (3, 200.00, '2023-05-28');

-- Sample data for Transfers table
INSERT INTO Transfers (Transaction_ID, Source_Account_ID, Destination_Account_ID, Amount, Transfer_Date)
VALUES
  (3, 1, 2, 200.00, '2023-05-28');
