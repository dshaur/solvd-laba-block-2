package com.solvd.block2.sql;

import com.solvd.block2.sql.daos.*;
import com.solvd.block2.sql.models.*;
import com.solvd.block2.sql.services.BranchService;
import com.solvd.block2.sql.services.CustomerService;
import com.solvd.block2.sql.services.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // Create instances of DAO and Service
        BranchDAO branchDAO = new BranchDAO();
        BranchEmployeeDAO branchEmployeeDAO = new BranchEmployeeDAO();
        BranchService branchService = new BranchService(branchDAO, branchEmployeeDAO);

        TransactionDAO transactionDAO = new TransactionDAO();
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO();
        TransactionService transactionService = new TransactionService(transactionDAO, transactionTypeDAO);

        CustomerDAO customerDAO = new CustomerDAO();
        DebitCardDAO debitCardDAO = new DebitCardDAO();
        CreditCardDAO creditCardDAO = new CreditCardDAO();
        LoanDAO loanDAO = new LoanDAO();
        CustomerService customerService = new CustomerService(customerDAO, debitCardDAO, creditCardDAO, loanDAO);

        /* ********************* Test display operations ********************* */

        // Get branches with ID 1
        int branchId = 1; // Change the branch ID as per schema data
        try {
            Branch branch = branchService.getBranchById(branchId);
            if (branch != null) {
                LOGGER.info("Branch found with ID: " + branchId);
                LOGGER.info("Branch Details: " + branch);
            } else {
                LOGGER.info("Branch not found with ID: " + branchId);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving branch with ID: " + branchId + ". " + e.getMessage());
        }

        // Get all branches by location
        String location = "New York"; // Change the location as per schema data
        try {
            List<Branch> branchesByLocation = branchService.getBranchesByLocation(location);
            if (!branchesByLocation.isEmpty()) {
                LOGGER.info("Branches found at location '" + location + "': " + branchesByLocation);
            } else {
                LOGGER.info("No branches found at location: " + location);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving branches by location: " + location + ". " + e.getMessage());
        }

        // Get all branch employees for a specific branch ID
        int employeeBranchId = 3; // Change the branch ID as per schema data
        try {
            List<BranchEmployee> branchEmployeesByBranchId = branchService.getBranchEmployeesByBranchId(employeeBranchId);
            if (!branchEmployeesByBranchId.isEmpty()) {
                LOGGER.info("Branch Employees found for branch ID " + employeeBranchId + ": " + branchEmployeesByBranchId);
            } else {
                LOGGER.info("No Branch Employees found for branch ID: " + employeeBranchId);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving Branch Employees by branch ID: " + employeeBranchId + ". " + e.getMessage());
        }

        // Get transaction by ID
        int transactionId = 1; // Change the transaction ID as per schema data
        try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            if (transaction != null) {
                LOGGER.info("Transaction found with ID: " + transactionId);
                LOGGER.info("Transaction Details: " + transaction);
            } else {
                LOGGER.info("Transaction not found with ID: " + transactionId);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving transaction with ID: " + transactionId + ". " + e.getMessage());
        }

        // Get all transaction types
        try {
            List<TransactionType> transactionTypes = transactionService.getAllTransactionTypes();
            if (!transactionTypes.isEmpty()) {
                LOGGER.info("Transaction Types:");
                for (TransactionType transactionType : transactionTypes) {
                    LOGGER.info(transactionType);
                }
            } else {
                LOGGER.info("No transaction types found.");
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving transaction types: " + e.getMessage());
        }

        // Get customer by ID
        int customerId = 1; // Change the customer ID as per schema data
        try {
            Customer customer = customerService.getCustomerById(customerId);
            if (customer != null) {
                LOGGER.info("Customer found with ID: " + customerId);
                LOGGER.info("Customer Details: " + customer);
            } else {
                LOGGER.info("Customer not found with ID: " + customerId);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving customer with ID: " + customerId + ". " + e.getMessage());
        }


        // Get debit cards by customer ID
        int debitCardCustomerId = 1; // Change the customer ID as per schema data
        try {
            List<DebitCard> debitCardsByCustomerId = customerService.getDebitCardsByCustomerId(debitCardCustomerId);
            if (!debitCardsByCustomerId.isEmpty()) {
                LOGGER.info("Debit Cards found for customer ID " + debitCardCustomerId + ": " + debitCardsByCustomerId);
            } else {
                LOGGER.info("No Debit Cards found for customer ID: " + debitCardCustomerId);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving Debit Cards by customer ID: " + debitCardCustomerId + ". " + e.getMessage());
        }


        // Get credit cards by customer ID
        int creditCardCustomerId = 1; // Change the customer ID as per schema data
        try {
            List<CreditCard> creditCardsByCustomerId = customerService.getCreditCardsByCustomerId(creditCardCustomerId);
            if (!creditCardsByCustomerId.isEmpty()) {
                LOGGER.info("Credit Cards found for customer ID " + creditCardCustomerId + ": " + creditCardsByCustomerId);
            } else {
                LOGGER.info("No Credit Cards found for customer ID: " + creditCardCustomerId);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving Credit Cards by customer ID: " + creditCardCustomerId + ". " + e.getMessage());
        }


        // Get loans by customer ID
        int loanCustomerId = 1; // Change the customer ID as per schema data
        try {
            List<Loan> loansByCustomerId = customerService.getLoansByCustomerId(loanCustomerId);
            if (!loansByCustomerId.isEmpty()) {
                LOGGER.info("Loans found for customer ID " + loanCustomerId + ": " + loansByCustomerId);
            } else {
                LOGGER.info("No Loans found for customer ID: " + loanCustomerId);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving Loans by customer ID: " + loanCustomerId + ". " + e.getMessage());
        }

    }
}






