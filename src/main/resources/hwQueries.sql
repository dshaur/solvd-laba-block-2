/*********************** UPDATE Statements ***********************/
/*
UPDATE Accounts
SET Balance = Balance + 1000.00
WHERE Account_ID = 1;

UPDATE Customers
SET Email = 'jane.newemail@example.com'
WHERE Customer_ID = 2;

UPDATE Credit_Cards
SET Outstanding_Balance = Outstanding_Balance - 500.00
WHERE credit_card_id = 1;

UPDATE Branches
SET Location = 'Miami'
WHERE Branch_ID = 3;

UPDATE Loans
SET Loan_Amount = 250000.00
WHERE Loan_ID = 1;

UPDATE Branch_Employees
SET Position = 'Manager'
WHERE Employee_ID = 2;

UPDATE Transactions
SET Amount = 75
WHERE Transaction_ID = 3;

UPDATE Debit_Cards
SET Expiry_Date = '2027-12-31'
WHERE Debit_Card_ID = 1;

UPDATE Accounts
SET Last_Transaction_Date = '2023-05-31'
WHERE Account_ID = 2;

UPDATE Credit_Cards
SET Credit_Limit = 4000.00
WHERE credit_card_id = 2;
*/
/*********************** DELETION Statements ***********************/
/*
DELETE FROM Customers
WHERE Customer_ID = 3;

DELETE FROM Accounts
WHERE Account_ID = 2;

DELETE FROM Credit_Cards
WHERE Credit_Card_ID = 2;

DELETE FROM Debit_Cards
WHERE Debit_Card_ID = 3;

DELETE FROM Loans
WHERE Loan_ID = 1;

DELETE FROM Branch_Employees
WHERE Employee_ID = 2;

DELETE FROM Transactions
WHERE Transaction_ID = 3;

DELETE FROM Deposits
WHERE Deposit_ID = 1;

DELETE FROM Withdrawals
WHERE Withdrawal_ID = 1;

DELETE FROM Transfers
WHERE Transfer_ID = 1;
*/

/*********************** ALTER Statements ***********************/
/*
ALTER TABLE Customers
ADD COLUMN Date_of_Birth DATE;

ALTER TABLE Accounts
MODIFY COLUMN Balance DECIMAL(10, 3);

ALTER TABLE Credit_Cards
ADD COLUMN CVV INT;

ALTER TABLE Branch_Employees
CHANGE COLUMN Position Employee_Position VARCHAR(50);

ALTER TABLE Branches
DROP COLUMN Branch_Name;

ALTER TABLE loans
DROP COLUMN loan_type;
*/

/*********************** BIG JOIN Statement ***********************/
SELECT *
FROM Customers
LEFT JOIN Account_Holders ON Account_Holders.Customer_ID = Customers.Customer_ID
LEFT JOIN Accounts ON Accounts.Account_ID = Account_Holders.Account_ID
LEFT JOIN Branches ON Branches.Branch_ID = Accounts.Branch_ID
LEFT JOIN Branch_Employees ON Branch_Employees.Branch_ID = Branches.Branch_ID
LEFT JOIN Credit_Cards ON Credit_Cards.Customer_ID = Customers.Customer_ID
LEFT JOIN Debit_Cards ON Debit_Cards.Customer_ID = Customers.Customer_ID
LEFT JOIN Transactions ON Transactions.Account_ID = Accounts.Account_ID
LEFT JOIN Transaction_Type ON Transaction_Type.Transaction_Type_ID = Transactions.Transaction_Type_ID
LEFT JOIN Loans ON Loans.Customer_ID = Customers.Customer_ID;

/*********************** JOINS Statements ***********************/

-- Left Join
SELECT *
FROM customers
LEFT JOIN account_holders ON account_holders.Customer_ID = customers.Customer_ID
LEFT JOIN accounts ON accounts.Account_ID = account_holders.Account_ID;

-- Right Join
SELECT *
FROM account_holders
RIGHT JOIN accounts ON accounts.Account_ID = account_holders.Account_ID
RIGHT JOIN customers ON customers.Customer_ID = account_holders.Customer_ID;

-- Inner Join
SELECT *
FROM transactions
INNER JOIN accounts ON transactions.Account_ID = accounts.Account_ID;

-- Outer Join
SELECT *
FROM branches
LEFT JOIN branch_employees ON branches.Branch_ID = branch_employees.Branch_ID
UNION
SELECT *
FROM branches
RIGHT JOIN branch_employees ON branches.Branch_ID = branch_employees.Branch_ID;

/*********************** AGGREGATE Statements (without HAVING) ***********************/

-- Get the total balance for each account type
SELECT Account_Type, SUM(Balance) AS Total_Balance
FROM accounts
GROUP BY Account_Type;

-- Get the number of customers in each branch
SELECT branches.Branch_Name, COUNT(customers.Customer_ID) AS Customer_Count
FROM branches
LEFT JOIN accounts ON accounts.Branch_ID = branches.Branch_ID
LEFT JOIN account_holders ON account_holders.Account_ID = accounts.Account_ID
LEFT JOIN customers ON customers.Customer_ID = account_holders.Customer_ID
GROUP BY branches.Branch_Name;

-- Get the average outstanding balance for credit cards
SELECT AVG(Outstanding_Balance) AS Average_Outstanding_Balance
FROM credit_cards;

-- Get the maximum loan amount for each customer
SELECT customers.Customer_ID, customers.First_Name, customers.Last_Name, MAX(loans.Loan_Amount) AS Max_Loan_Amount
FROM customers
LEFT JOIN loans ON loans.Customer_ID = customers.Customer_ID
GROUP BY customers.Customer_ID, customers.First_Name, customers.Last_Name;

-- Get the minimum transaction amount for each transaction type
SELECT transactions.Transaction_Type, MIN(transactions.Amount) AS Min_Transaction_Amount
FROM transactions
GROUP BY transactions.Transaction_Type;

--  Get the total number of deposits for each account
SELECT accounts.Account_ID, COUNT(deposits.Deposit_ID) AS Total_Deposits
FROM accounts
LEFT JOIN transactions ON transactions.Account_ID = accounts.Account_ID
LEFT JOIN deposits ON deposits.Transaction_ID = transactions.Transaction_ID
GROUP BY accounts.Account_ID;

-- Get the average interest rate for loans
SELECT AVG(Interest_Rate) AS Average_Interest_Rate
FROM loans;

/*********************** AGGREGATE Statements (with HAVING) ***********************/

-- Get the account types with a total balance greater than 5000
SELECT Account_Type, SUM(Balance) AS Total_Balance
FROM accounts
GROUP BY Account_Type
HAVING Total_Balance > 5000;

-- Get the branches with at least 1 customer
SELECT branches.Branch_Name, COUNT(customers.Customer_ID) AS Customer_Count
FROM branches
LEFT JOIN accounts ON accounts.Branch_ID = branches.Branch_ID
LEFT JOIN account_holders ON account_holders.Account_ID = accounts.Account_ID
LEFT JOIN customers ON customers.Customer_ID = account_holders.Customer_ID
GROUP BY branches.Branch_Name
HAVING Customer_Count >= 1;

-- Get the average outstanding balance for credit cards greater than $300
SELECT AVG(Outstanding_Balance) AS Average_Outstanding_Balance
FROM credit_cards
HAVING Average_Outstanding_Balance > 300;

-- Get the maximum loan amount for each customer, only including customers with a maximum loan amount greater than $50000
SELECT customers.Customer_ID, MAX(loans.Loan_Amount) AS Max_Loan_Amount
FROM customers
LEFT JOIN loans ON loans.Customer_ID = customers.Customer_ID
GROUP BY customers.Customer_ID
HAVING Max_Loan_Amount > 50000;

-- Get the minimum transaction amount for each transaction type, only including transactions with a minimum amount less than $400
SELECT transactions.Transaction_Type, MIN(transactions.Amount) AS Min_Transaction_Amount
FROM transactions
GROUP BY transactions.Transaction_Type
HAVING Min_Transaction_Amount < 400;

-- Get the total number of deposits for each account, only including accounts with a total number of deposits greater than 0
SELECT accounts.Account_ID, COUNT(deposits.Deposit_ID) AS Total_Deposits
FROM accounts
LEFT JOIN transactions ON transactions.Account_ID = accounts.Account_ID
LEFT JOIN deposits ON deposits.Transaction_ID = transactions.Transaction_ID
GROUP BY accounts.Account_ID
HAVING Total_Deposits > 0;

-- Get the branches with an average account balance greater than $1000
SELECT branches.Branch_Name, AVG(accounts.Balance) AS Average_Balance
FROM branches
LEFT JOIN accounts ON accounts.Branch_ID = branches.Branch_ID
GROUP BY branches.Branch_Name
HAVING Average_Balance > 1000;













