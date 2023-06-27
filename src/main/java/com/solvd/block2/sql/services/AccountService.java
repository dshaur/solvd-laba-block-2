package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.AccountDAO;
import com.solvd.block2.sql.models.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountService implements IAccountService {
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public AccountService() {
        // No argument constructor
    }

    public Account getAccountById(int accountId) throws SQLException {
        Account account = accountDAO.getAccountById(accountId);
        return account;
    }

    public List<Account> getAllAccounts() throws SQLException {
        return accountDAO.getAllAccounts();
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

    public List<Account> getAccountsByCustomerId(int customerId) throws SQLException {
        return accountDAO.getByCustomerId(customerId);
    }

    public List<Account> getAccountsByBranchId(int branchId) throws SQLException {
        return accountDAO.getAccountsByBranchId(branchId);
    }
}
