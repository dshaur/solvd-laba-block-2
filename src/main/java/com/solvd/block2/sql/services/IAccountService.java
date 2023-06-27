package com.solvd.block2.sql.services;

import com.solvd.block2.sql.models.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountService {
    Account getAccountById(int id) throws SQLException;

    List<Account> getAllAccounts() throws SQLException;

    void createAccount(Account account) throws SQLException;

    void updateAccount(Account account) throws SQLException;

    void deleteAccount(Account account) throws SQLException;
}
