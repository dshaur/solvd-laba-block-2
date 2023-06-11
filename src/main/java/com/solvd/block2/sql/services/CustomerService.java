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

    public Customer getCustomerById(int customerId) {
        Customer customer = customerDAO.getById(customerId);
        customer.setDebitCards(debitCardDAO.getByCustomerId(customerId));
        customer.setCreditCards(creditCardDAO.getByCustomerId(customerId));
        customer.setLoans(loanDAO.getByCustomerId(customerId));
        return customer;
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

    public List<Loan> getLoansByCustomerId(int loanCustomerId) {
        return loanDAO.getByCustomerId(loanCustomerId);
    }

    public List<CreditCard> getCreditCardsByCustomerId(int creditCardCustomerId) {
        return creditCardDAO.getByCustomerId(creditCardCustomerId);
    }

    public List<DebitCard> getDebitCardsByCustomerId(int debitCardCustomerId) {
        return debitCardDAO.getByCustomerId(debitCardCustomerId);
    }

    // Other methods
}



