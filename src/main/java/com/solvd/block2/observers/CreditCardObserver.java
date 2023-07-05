package com.solvd.block2.observers;

import com.solvd.block2.sql.models.CreditCard;

public interface CreditCardObserver {

    void onCreditCardCreated(CreditCard creditCard);

}
