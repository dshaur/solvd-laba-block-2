package com.solvd.block2.service_factory;

import com.solvd.block2.sql.models.Account;
import com.solvd.block2.sql.models.Branch;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.models.Transaction;
import com.solvd.block2.sql.services.IAccountService;
import com.solvd.block2.sql.services.IBranchService;
import com.solvd.block2.sql.services.ICustomerService;
import com.solvd.block2.sql.services.ITransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ServiceFactoryRunner {

    private static final Logger LOGGER = LogManager.getLogger(ServiceFactoryRunner.class);

    public static void main(String[] args) {
        // Choose the type of service you want to create
        String serviceType = "myBatis"; // or "mybatis"

        // Create the corresponding service using the ServiceFactory
        ICustomerService customerService = ServiceFactory.createCustomerService(serviceType);
        IBranchService branchService = ServiceFactory.createBranchService(serviceType);
        IAccountService accountService = ServiceFactory.createAccountService(serviceType);
        ITransactionService transactionService = ServiceFactory.createTransactionService(serviceType);

        try {
            // Test the customer service
            assert customerService != null;
            List<Customer> customers = customerService.getAllCustomers();
            for (Customer customer : customers) {
                LOGGER.info(customer);
            }

            // Test the branch service
            assert branchService != null;
            List<Branch> branches = branchService.getAllBranches();
            for (Branch branch : branches) {
                LOGGER.info(branch);
            }

            // Test the account service
            assert accountService != null;
            List<Account> accounts = accountService.getAllAccounts();
            for (Account account : accounts) {
                LOGGER.info(account);
            }

            // Test the transaction service
            assert transactionService != null;
            Transaction transaction = transactionService.getTransactionById(1);
            LOGGER.info(transaction);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
