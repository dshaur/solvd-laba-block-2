package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.CreditCard;

import java.util.List;

public interface ICreditCardDAO {
    CreditCard getById(int creditCardId);

    List<CreditCard> getByCustomerId(int customerId);

    List<CreditCard> getAll();

    void create(CreditCard creditCard);

    void update(CreditCard creditCard);

    void delete(CreditCard creditCard);
}


