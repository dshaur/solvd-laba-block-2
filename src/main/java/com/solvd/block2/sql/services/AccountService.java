package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.AccountDAO;
import com.solvd.block2.sql.models.Account;
import com.solvd.block2.sql.models.Customer;

import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;
    private CustomerService customerService;

    public AccountService(AccountDAO accountDAO, CustomerService customerService) {
        this.accountDAO = accountDAO;
        this.customerService = customerService;
    }

    public Account getAccountById(int accountId) throws SQLException {
        Account account = accountDAO.getAccountById(accountId);
        if (account != null) {
            List<Customer> customers = customerService.getCustomersByAccountId(accountId);
            account.setCustomers(customers);
        }
        return account;
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = accountDAO.getAllAccounts();
        for (Account account : accounts) {
            List<Customer> customers = customerService.getCustomersByAccountId(account.getAccountId());
            account.setCustomers(customers);
        }
        return accounts;
    }

    public void createAccount(Account account) throws SQLException {
        accountDAO.create(account);
    }

    public void updateAccount(Account account) throws SQLException {
        accountDAO.updateAccount(account);
    }

    public void deleteAccount(Account account) throws SQLException {
        accountDAO.deleteAccount(account.getAccountId());
    }

    public List<Customer> getCustomersByAccountId(int accountId) throws SQLException {
        return accountDAO.getCustomersByAccountId(accountId);
    }

    public List<Account> getAccountsByCustomerId(int customerId) throws SQLException {
        return accountDAO.getByCustomerId(customerId);
    }

    public List<Account> getAccountsByBranchId(int branchId) throws SQLException {
        return accountDAO.getAccountsByBranchId(branchId);
    }
}
