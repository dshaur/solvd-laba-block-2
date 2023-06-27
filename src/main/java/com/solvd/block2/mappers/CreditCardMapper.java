package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.CreditCard;

import java.util.List;

public interface CreditCardMapper {
    CreditCard getCreditCardById(int id);

    List<CreditCard> getAllCreditCards();

    List<CreditCard> getCreditCardsByCustomerId(int customerId);

    void createCreditCard(CreditCard creditCard);

    void updateCreditCard(CreditCard creditCard);

    void deleteCreditCard(CreditCard creditCard);
}

