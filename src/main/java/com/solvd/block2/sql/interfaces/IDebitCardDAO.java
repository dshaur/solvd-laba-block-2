package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.DebitCard;

import java.sql.SQLException;
import java.util.List;

public interface IDebitCardDAO extends GenDAO<DebitCard> {
    DebitCard getById(int debitCardId) throws SQLException;

    List<DebitCard> getByCustomerId(int customerId) throws SQLException;

    List<DebitCard> getAll() throws SQLException;

    void create(DebitCard debitCard) throws SQLException;

    void update(DebitCard debitCard) throws SQLException;

    void delete(DebitCard debitCard) throws SQLException;
}

