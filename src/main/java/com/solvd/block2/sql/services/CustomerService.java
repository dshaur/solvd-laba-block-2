package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.CreditCardDAO;
import com.solvd.block2.sql.daos.CustomerDAO;
import com.solvd.block2.sql.daos.DebitCardDAO;
import com.solvd.block2.sql.daos.LoanDAO;
import com.solvd.block2.sql.models.CreditCard;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.models.DebitCard;
import com.solvd.block2.sql.models.Loan;

import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO;
    private DebitCardDAO debitCardDAO;
    private CreditCardDAO creditCardDAO;
    private LoanDAO loanDAO;

    public CustomerService(CustomerDAO customerDAO, DebitCardDAO debitCardDAO, CreditCardDAO creditCardDAO, LoanDAO loanDAO) {
        this.customerDAO = customerDAO;
        this.debitCardDAO = debitCardDAO;
        this.creditCardDAO = creditCardDAO;
        this.loanDAO = loanDAO;
    }

    // Customer methods
    public Customer getCustomerById(int customerId) {
        return customerDAO.getById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    public void createCustomer(Customer customer) {
        customerDAO.create(customer);
    }

    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerDAO.delete(customer);
    }

    // DebitCard methods
    public DebitCard getDebitCardById(int debitCardId) {
        return debitCardDAO.getById(debitCardId);
    }

    public List<DebitCard> getDebitCardsByCustomerId(int customerId) {
        return debitCardDAO.getByCustomerId(customerId);
    }

    public List<DebitCard> getAllDebitCards() {
        return debitCardDAO.getAll();
    }

    public void createDebitCard(DebitCard debitCard) {
        debitCardDAO.create(debitCard);
    }

    public void updateDebitCard(DebitCard debitCard) {
        debitCardDAO.update(debitCard);
    }

    public void deleteDebitCard(DebitCard debitCard) {
        debitCardDAO.delete(debitCard);
    }

    // CreditCard methods
    public CreditCard getCreditCardById(int creditCardId) {
        return creditCardDAO.getById(creditCardId);
    }

    public List<CreditCard> getCreditCardsByCustomerId(int customerId) {
        return creditCardDAO.getByCustomerId(customerId);
    }

    public List<CreditCard> getAllCreditCards() {
        return creditCardDAO.getAll();
    }

    public void createCreditCard(CreditCard creditCard) {
        creditCardDAO.create(creditCard);
    }

    public void updateCreditCard(CreditCard creditCard) {
        creditCardDAO.update(creditCard);
    }

    public void deleteCreditCard(CreditCard creditCard) {
        creditCardDAO.delete(creditCard);
    }

    // Loan methods
    public Loan getLoanById(int loanId) {
        return loanDAO.getById(loanId);
    }

    public List<Loan> getLoansByCustomerId(int customerId) {
        return loanDAO.getByCustomerId(customerId);
    }

    public List<Loan> getAllLoans() {
        return loanDAO.getAll();
    }

    public void createLoan(Loan loan) {
        loanDAO.create(loan);
    }

    public void updateLoan(Loan loan) {
        loanDAO.update(loan);
    }

    public void deleteLoan(Loan loan) {
        loanDAO.delete(loan);
    }
}

