package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.*;
import com.solvd.block2.sql.models.*;

import java.sql.SQLException;
import java.util.List;

public class CustomerService implements ICustomerService {
    private CustomerDAO customerDAO;
    private DebitCardDAO debitCardDAO;
    private CreditCardDAO creditCardDAO;
    private LoanDAO loanDAO;
    private AccountDAO accountDAO;

    public CustomerService() {
        // No argument constructor
    }

    public CustomerService(CustomerDAO customerDAO, DebitCardDAO debitCardDAO, CreditCardDAO creditCardDAO, LoanDAO loanDAO, AccountDAO accountDAO) {
        this.customerDAO = customerDAO;
        this.debitCardDAO = debitCardDAO;
        this.creditCardDAO = creditCardDAO;
        this.loanDAO = loanDAO;
        this.accountDAO = accountDAO;
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        Customer customer = customerDAO.getById(customerId);
        customer.setDebitCards(debitCardDAO.getByCustomerId(customerId));
        customer.setCreditCards(creditCardDAO.getByCustomerId(customerId));
        customer.setLoans(loanDAO.getByCustomerId(customerId));
        customer.setAccounts(accountDAO.getByCustomerId(customerId));
        return customer;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAll();
    }

    public void createCustomer(Customer customer) throws SQLException {
        customerDAO.create(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.update(customer);
    }

    public void deleteCustomer(Customer customer) throws SQLException {
        customerDAO.delete(customer);
    }

    public List<Loan> getLoansByCustomerId(int loanCustomerId) throws SQLException {
        return loanDAO.getByCustomerId(loanCustomerId);
    }

    public List<CreditCard> getCreditCardsByCustomerId(int creditCardCustomerId) throws SQLException {
        return creditCardDAO.getByCustomerId(creditCardCustomerId);
    }

    public List<DebitCard> getDebitCardsByCustomerId(int debitCardCustomerId) throws SQLException {
        return debitCardDAO.getByCustomerId(debitCardCustomerId);
    }

    public void createCreditCard(CreditCard creditCard) throws SQLException {
        creditCardDAO.create(creditCard);
    }

    public void createDebitCard(DebitCard debitCard) throws SQLException {
        debitCardDAO.create(debitCard);
    }

    public void createLoan(Loan loan) throws SQLException {
        loanDAO.create(loan);
    }

    public void updateCreditCard(CreditCard creditCard) throws SQLException {
        creditCardDAO.update(creditCard);
    }

    public void updateDebitCard(DebitCard debitCard) throws SQLException {
        debitCardDAO.update(debitCard);
    }

    public void updateLoan(Loan loan) throws SQLException {
        loanDAO.update(loan);
    }

    public void deleteCreditCard(CreditCard creditCard) throws SQLException {
        creditCardDAO.delete(creditCard);
    }

    public void deleteDebitCard(DebitCard debitCard) throws SQLException {
        debitCardDAO.delete(debitCard);
    }

    public void deleteLoan(Loan loan) throws SQLException {
        loanDAO.delete(loan);
    }

    public List<Account> getAccountsByCustomerId(int customerId) throws SQLException {
        return accountDAO.getByCustomerId(customerId);
    }

    public void createAccount(Account account) throws SQLException {
        accountDAO.create(account);
    }

    public void updateAccount(Account account) throws SQLException {
        accountDAO.update(account);
    }

    public void deleteAccount(Account account) throws SQLException {
        accountDAO.delete(account);
    }


}



