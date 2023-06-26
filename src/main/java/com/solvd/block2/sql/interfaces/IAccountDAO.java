package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountDAO {

    Account getAccountById(int accountId) throws SQLException;

    void updateAccount(Account account);

    void deleteAccount(int accountId);

    List<Account> getAllAccounts() throws SQLException;

    List<Account> getByCustomerId(int customerId) throws SQLException;

    List<Account> getAccountsByBranchId(int branchId) throws SQLException;
}

