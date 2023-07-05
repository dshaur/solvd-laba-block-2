package com.solvd.block2.observers;

import com.solvd.block2.sql.models.DebitCard;

public interface DebitCardObserver {

    void onDebitCardCreated(DebitCard debitCard);

}
