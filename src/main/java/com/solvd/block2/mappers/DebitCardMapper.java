package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.DebitCard;

import java.util.List;

public interface DebitCardMapper {
    DebitCard getDebitCardById(int debitCardId);

    List<DebitCard> getAllDebitCards();

    void insertDebitCard(DebitCard debitCard);

    void updateDebitCard(DebitCard debitCard);

    void deleteDebitCard(int debitCardId);

    List<DebitCard> getDebitCardsByCustomerId(int customerId);
}

