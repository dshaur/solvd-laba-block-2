package com.solvd.block2.observers;

import com.solvd.block2.sql.models.DebitCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebitCardLogger implements DebitCardObserver {

    private static final Logger LOGGER = LogManager.getLogger(DebitCardLogger.class);

    @Override
    public void onDebitCardCreated(DebitCard debitCard) {
        LOGGER.info("New debit card created: " + "Customer ID: " + debitCard.getCustomerId() + ", Debit Card Number: " + debitCard.getCardNumber()
                + ", Expiration Date: " + debitCard.getExpirationDate());
    }

}
