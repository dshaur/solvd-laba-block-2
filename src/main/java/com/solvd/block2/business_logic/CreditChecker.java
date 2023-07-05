package com.solvd.block2.business_logic;

import com.solvd.block2.observers.*;
import com.solvd.block2.sql.models.*;
import com.solvd.block2.sql.services.ICustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreditChecker {

    private static final Logger LOGGER = LogManager.getLogger(CreditChecker.class);

    private List<CustomerObserver> customerObservers = new ArrayList<>();
    private List<AccountObserver> accountObservers = new ArrayList<>();
    private List<DebitCardObserver> debitCardObservers = new ArrayList<>();
    private List<CreditCardObserver> creditCardObservers = new ArrayList<>();
    private List<LoanObserver> loanObservers = new ArrayList<>();

    private final ICustomerService customerService;

    public CreditChecker(ICustomerService customerService) {
        this.customerService = customerService;
    }

    // Register observers
    public void registerCustomerObserver(CustomerObserver observer) {
        customerObservers.add(observer);
    }

    public void registerAccountObserver(AccountObserver observer) {
        accountObservers.add(observer);
    }

    public void registerDebitCardObserver(DebitCardObserver observer) {
        debitCardObservers.add(observer);
    }

    public void registerCreditCardObserver(CreditCardObserver observer) {
        creditCardObservers.add(observer);
    }

    public void registerLoanObserver(LoanObserver observer) {
        loanObservers.add(observer);
    }

    // Notify all observers

    private void notifyCustomerObservers(Customer customer) {
        for (CustomerObserver observer : customerObservers) {
            observer.onCustomerCreated(customer);
        }
    }

    private void notifyAccountObservers(Account account) {
        for (AccountObserver observer : accountObservers) {
            observer.onAccountCreated(account);
        }
    }

    private void notifyDebitCardObservers(DebitCard debitCard) {
        for (DebitCardObserver observer : debitCardObservers) {
            observer.onDebitCardCreated(debitCard);
        }
    }

    private void notifyCreditCardObservers(CreditCard creditCard) {
        for (CreditCardObserver observer : creditCardObservers) {
            observer.onCreditCardCreated(creditCard);
        }
    }

    private void notifyLoanObservers(Loan loan) {
        for (LoanObserver observer : loanObservers) {
            observer.onLoanCreated(loan);
        }
    }


    public void checkCreditScore(Customer customer, int creditScore) throws SQLException {
        if (creditScore >= 700) {
            LOGGER.info("Credit approved for an account!");

            // Register the customer in the database and get the id
            int lastAddedCustomerId = insertCustomerAndGetId(customer);
            customer.setCustomerId(lastAddedCustomerId);
            notifyCustomerObservers(customer);

            // Register the new account in the database
            Account account = registerAccount();
            customerService.createAccount(account);
            notifyAccountObservers(account);

            // Register the new debit card in the database
            DebitCard debitCard = registerDebitCard(lastAddedCustomerId);
            customerService.createDebitCard(debitCard);
            notifyDebitCardObservers(debitCard);

            // Check credit score for credit card approval
            if (creditScore >= 750) {
                LOGGER.info("Credit approved for Credit Card!");

                // Register the new credit card in the database
                CreditCard creditCard = registerCreditCard(lastAddedCustomerId);
                customerService.createCreditCard(creditCard);
                notifyCreditCardObservers(creditCard);
            }

            // Check credit score for loan approval
            if (creditScore >= 800) {
                LOGGER.info("Credit approved for Loans!");

                // Register the new loan in the database
                Loan loan = registerLoan(lastAddedCustomerId);
                customerService.createLoan(loan);
                notifyLoanObservers(loan);
            }
        } else {
            LOGGER.info("Credit score is too low to apply for an account.");
            LOGGER.info("Customer: " + customer.getFirstName() + " " + customer.getLastName());
            LOGGER.info("Credit Score: " + creditScore);
        }
    }

    private int insertCustomerAndGetId(Customer customer) throws SQLException {
        customerService.createCustomer(customer);
        List<Customer> customers = customerService.getAllCustomers();
        Customer lastAddedCustomer = customers.get(customers.size() - 1);
        return lastAddedCustomer.getCustomerId();
    }

    private Account registerAccount() {
        Account account = new Account();
        account.setAccountType(RandomHelper.getRandomAccountType());
        account.setBalance(RandomHelper.getRandomAccountBalance());
        account.setOpenDate(Date.valueOf(LocalDate.now()));
        account.setLastTransactionDate(Date.valueOf(LocalDate.now()));
        account.setBranchId(RandomHelper.getRandomBranchId());
        return account;
    }

    private DebitCard registerDebitCard(int customerId) {
        DebitCard debitCard = new DebitCard();
        debitCard.setCustomerId(customerId);
        debitCard.setCardNumber(RandomHelper.generateRandomCardNumber());
        debitCard.setExpirationDate(RandomHelper.generateRandomExpiryDate());
        return debitCard;
    }

    private CreditCard registerCreditCard(int customerId) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCustomerId(customerId);
        creditCard.setCreditCardNumber(RandomHelper.generateRandomCardNumber());
        creditCard.setExpiryDate(RandomHelper.generateRandomExpiryDate());
        creditCard.setCreditLimit(RandomHelper.getRandomCreditLimit());
        creditCard.setOutstandingBalance(0);
        return creditCard;
    }

    private Loan registerLoan(int customerId) {
        Loan loan = new Loan();
        loan.setCustomerId(customerId);
        loan.setLoanType(RandomHelper.getRandomLoanType());
        loan.setLoanAmount(RandomHelper.getRandomLoanAmount());
        loan.setInterestRate(RandomHelper.getRandomInterestRate());
        loan.setStartDate(Date.valueOf(LocalDate.now()));
        if (loan.getLoanType().equals("Car Loan")) {
            loan.setEndDate(Date.valueOf(LocalDate.now().plusYears(7)));
        } else if (loan.getLoanType().equals("Home Loan")) {
            loan.setEndDate(Date.valueOf(LocalDate.now().plusYears(30)));
        }
        return loan;
    }
}