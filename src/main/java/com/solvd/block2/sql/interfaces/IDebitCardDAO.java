package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.DebitCard;

import java.util.List;

public interface IDebitCardDAO extends GenDAO<DebitCard> {
    DebitCard getById(int cardId);

    List<DebitCard> getByCustomerId(int customerId);

    List<DebitCard> getAll();

    void create(DebitCard debitCard);

    void update(DebitCard debitCard);

    void delete(DebitCard debitCard);
}

