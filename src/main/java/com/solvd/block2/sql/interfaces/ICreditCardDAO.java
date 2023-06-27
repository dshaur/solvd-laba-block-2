package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.CreditCard;

import java.sql.SQLException;
import java.util.List;

public interface ICreditCardDAO {
    CreditCard getById(int creditCardId) throws SQLException;

    List<CreditCard> getByCustomerId(int customerId) throws SQLException;

    List<CreditCard> getAll() throws SQLException;

    void create(CreditCard creditCard) throws SQLException;

    void update(CreditCard creditCard) throws SQLException;

    void delete(CreditCard creditCard) throws SQLException;
}


