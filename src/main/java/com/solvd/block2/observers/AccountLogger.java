package com.solvd.block2.observers;

import com.solvd.block2.sql.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountLogger implements AccountObserver {

    private static final Logger LOGGER = LogManager.getLogger(AccountLogger.class);

    @Override
    public void onAccountCreated(Account account) {
        LOGGER.info("New account created: " + "Account Type: " + account.getAccountType() + ", Balance: "
                + account.getBalance() + ", Open Date: " + account.getOpenDate() + ", Branch ID: " + account.getBranchId());
    }
}
