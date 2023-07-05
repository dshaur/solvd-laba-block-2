package com.solvd.block2.observers;

import com.solvd.block2.sql.models.CreditCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditCardLogger implements CreditCardObserver {

    private static final Logger LOGGER = LogManager.getLogger(CreditCardLogger.class);

    @Override
    public void onCreditCardCreated(CreditCard creditCard) {
        LOGGER.info("New credit card created: " + "Customer ID: " + creditCard.getCustomerId() + ", Credit Card Number: "
                + creditCard.getCreditCardNumber() + ", Expiration Date: " + creditCard.getExpiryDate()
                + ", Credit Limit: " + creditCard.getCreditLimit() + ", Outstanding Balance: " + creditCard.getOutstandingBalance());
    }
}
