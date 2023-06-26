package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.Account;
import com.solvd.block2.sql.models.Customer;

import java.util.List;

public interface AccountMapper {
    Account getAccountById(int accountId);

    List<Account> getAllAccounts();

    void createAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(int accountId);

    List<Customer> getCustomersByAccountId(int accountId);

    List<Account> getAccountsByCustomerId(int customerId);

    List<Account> getAccountsByBranchId(int branchId);

    void insertAccount(Account account);
}

