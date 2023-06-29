package com.solvd.block2.observers;

import com.solvd.block2.sql.models.Account;

public interface AccountObserver {

    void onAccountCreated(Account account);

}
