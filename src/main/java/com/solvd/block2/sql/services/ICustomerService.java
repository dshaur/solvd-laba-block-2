package com.solvd.block2.sql.services;

import com.solvd.block2.sql.models.*;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {
    Customer getCustomerById(int customerId) throws SQLException;

    List<Customer> getAllCustomers() throws SQLException;

    void createCustomer(Customer customer) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(Customer customer) throws SQLException;

    List<Loan> getLoansByCustomerId(int id) throws SQLException;

    List<CreditCard> getCreditCardsByCustomerId(int id) throws SQLException;

    List<DebitCard> getDebitCardsByCustomerId(int id) throws SQLException;

    void createCreditCard(CreditCard creditCard) throws SQLException;

    void createDebitCard(DebitCard debitCard) throws SQLException;

    void createLoan(Loan loan) throws SQLException;

    void updateCreditCard(CreditCard creditCard) throws SQLException;

    void updateDebitCard(DebitCard debitCard) throws SQLException;

    void updateLoan(Loan loan) throws SQLException;

    void deleteCreditCard(CreditCard creditCard) throws SQLException;

    void deleteDebitCard(DebitCard debitCard) throws SQLException;

    void deleteLoan(Loan loan) throws SQLException;

    List<Account> getAccountsByCustomerId(int id) throws SQLException;

    void createAccount(Account account) throws SQLException;

    void updateAccount(Account account) throws SQLException;

    void deleteAccount(Account account) throws SQLException;

}
