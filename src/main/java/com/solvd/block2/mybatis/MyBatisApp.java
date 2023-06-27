package com.solvd.block2.mybatis;

import com.solvd.block2.sql.models.*;
import com.solvd.block2.sql.services.MyBatisAccountService;
import com.solvd.block2.sql.services.MyBatisBranchService;
import com.solvd.block2.sql.services.MyBatisCustomerService;
import com.solvd.block2.sql.services.MyBatisTransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBatisApp {

    public static final Logger LOGGER = LogManager.getLogger(MyBatisApp.class);

    public static void main(String[] args) {

        // ******** Create MyBatis services ********

        MyBatisBranchService branchService = new MyBatisBranchService();
        MyBatisCustomerService customerService = new MyBatisCustomerService();
        MyBatisTransactionService transactionService = new MyBatisTransactionService();
        MyBatisAccountService accountService = new MyBatisAccountService();
        // ************************ Test services ************************

        // ******** Branch methods ********

        // Get branch by branch id
        Branch branch = branchService.getBranchById(1);
        LOGGER.info("Branch from bankDB with id 1: " + branch);

        // Get all branches
        LOGGER.info("All Branches: ");
        branchService.getAllBranches().forEach(LOGGER::info);

        // Get branches by location
        String location = "New York";
        LOGGER.info("Branches in {}: ", location);
        branchService.getBranchesByLocation(location).forEach(LOGGER::info);

        // ******** BranchEmployee methods ********

        // Get branch employee by employee id
        BranchEmployee branchEmployee = branchService.getBranchEmployeeById(1);
        LOGGER.info("Branch Employee from bankDB with id 1: " + branchEmployee);

        // Get all branch employees
        LOGGER.info("All Branch Employees: ");
        branchService.getAllBranchEmployees().forEach(LOGGER::info);

        // Get branch employees by branch id
        LOGGER.info("Branch Employees working in Branch with id 1: ");
        branchService.getBranchEmployeesByBranchId(1).forEach(LOGGER::info);

        // ******** Customer methods ********

        // Get customer by customer id
        Customer customer = customerService.getCustomerById(3);
        LOGGER.info("Customer from bankDB with id 3: " + customer);

        // Get all customers
        LOGGER.info("All Customers: ");
        customerService.getAllCustomers().forEach(LOGGER::info);

        // ******** Credit Card methods ********

        // Get credit card by credit card id
        CreditCard creditCard = customerService.getCreditCardById(1);
        LOGGER.info("Credit Card from bankDB with id 1: " + creditCard);

        // Get credit cards by customer id
        LOGGER.info("Credit Cards for Customer with id 1: ");
        customerService.getCreditCardsByCustomerId(1).forEach(LOGGER::info);

        // ******** Debit Card methods ********

        // Get debit card by debit card id
        DebitCard debitCard = customerService.getDebitCardById(1);
        LOGGER.info("Debit Card from bankDB with id 1: " + debitCard);

        // Get debit cards by customer id
        LOGGER.info("Debit Cards for Customer with id 3: ");
        customerService.getDebitCardsByCustomerId(3).forEach(LOGGER::info);

        // ******** Loan methods ********

        // Get loan by loan id
        Loan loan = customerService.getLoanById(1);
        LOGGER.info("Loan from bankDB with id 1: " + loan);

        // Get loans by customer id
        LOGGER.info("Loans for Customer with id 3: ");
        customerService.getLoansByCustomerId(3).forEach(LOGGER::info);

        // ******** Transaction methods ********

        // Get transaction by transaction id
        Transaction transaction = transactionService.getTransactionById(1);
        LOGGER.info("Transaction from bankDB with id 1: " + transaction);

        // Get all transactions
        LOGGER.info("All Transactions: ");
        transactionService.getAllTransactions().forEach(LOGGER::info);

        // Get transactions by transaction type id
        int transactionTypeId = 1;
        LOGGER.info("Transactions with Transaction Type id {}: ", transactionTypeId);
        transactionService.getTransactionsByTransactionTypeId(transactionTypeId).forEach(LOGGER::info);

        // ********** Transaction Type methods **********

        // Get transaction type by transaction type id
        TransactionType transactionType = transactionService.getTransactionTypeById(1);
        LOGGER.info("Transaction Type from bankDB with id 1: " + transactionType);

        // Get all transaction types
        LOGGER.info("All Transaction Types: ");
        transactionService.getAllTransactionTypes().forEach(LOGGER::info);

        // ********** Account methods **********

        // Get account by account id
        Account account = accountService.getAccountById(1);
        LOGGER.info("Account from bankDB with id 1: " + account);

        // Get all accounts
        LOGGER.info("All Accounts: ");
        accountService.getAllAccounts().forEach(LOGGER::info);

    }
}

