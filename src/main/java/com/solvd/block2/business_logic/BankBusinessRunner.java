package com.solvd.block2.business_logic;

import com.solvd.block2.observers.*;
import com.solvd.block2.service_factory.ServiceFactory;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.services.ICustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class BankBusinessRunner {

    private static final Logger LOGGER = LogManager.getLogger(BankBusinessRunner.class);
    private static ICustomerService customerService = ServiceFactory.createCustomerService("mybatis");
    CreditChecker creditChecker = new CreditChecker(customerService);

    public static void main(String[] args) throws SQLException {
        BankBusinessRunner bankBusinessRunner = new BankBusinessRunner();
        bankBusinessRunner.runCreditCheck();

    }

    public void runCreditCheck() {

        // Create a potential customer with their basic information
        Customer customer = new Customer("Juan", "Domingo", "2236 Newberry Rd", "1235487889", "jdomingo45@gmail.com");
        // Provide the potential customer's credit score
        int creditScore = 763;

        // Create observers
        CustomerObserver customerLogger = new CustomerLogger();
        AccountObserver accountLogger = new AccountLogger();
        DebitCardObserver debitCardLogger = new DebitCardLogger();
        CreditCardObserver creditCardLogger = new CreditCardLogger();
        LoanObserver loanLogger = new LoanLogger();

        // Register the observers with the credit checker
        creditChecker.registerCustomerObserver(customerLogger);
        creditChecker.registerAccountObserver(accountLogger);
        creditChecker.registerDebitCardObserver(debitCardLogger);
        creditChecker.registerCreditCardObserver(creditCardLogger);
        creditChecker.registerLoanObserver(loanLogger);

        try {
            creditChecker.checkCreditScore(customer, creditScore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
