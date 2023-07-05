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

        // Set the system property "service.type" to choose the service type
        // This can also be accomplished in the command line with the following argument: java -Dservice.type=mybatis
        // One can also set an environment variable with the desired service type before running the application
        // Another alternative is to use the IDE to set the service type at run time

        System.setProperty("service.type", "mybatis"); // Change "mybatis" to "dao" for a different service type

        // Create the corresponding service using the ServiceFactory
        ICustomerService customerService = ServiceFactory.createCustomerService();
        IBranchService branchService = ServiceFactory.createBranchService();
        IAccountService accountService = ServiceFactory.createAccountService();
        ITransactionService transactionService = ServiceFactory.createTransactionService();

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
