package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.DebitCard;

import java.util.List;

public interface DebitCardMapper {
    DebitCard getDebitCardById(int debitCardId);

    List<DebitCard> getAllDebitCards();

    void createDebitCard(DebitCard debitCard);

    void updateDebitCard(DebitCard debitCard);

    void deleteDebitCard(DebitCard debitCardId);

    List<DebitCard> getDebitCardsByCustomerId(int customerId);
}

