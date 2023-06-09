package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountDAO {

    Account getAccountById(int accountId) throws SQLException;

    void create(Account account) throws SQLException;

    void update(Account account) throws SQLException;

    void delete(Account account) throws SQLException;

    List<Account> getAllAccounts() throws SQLException;

    List<Account> getByCustomerId(int customerId) throws SQLException;

    List<Account> getAccountsByBranchId(int branchId) throws SQLException;
}

