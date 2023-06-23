package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.TransactionType;

import java.sql.SQLException;

public interface ITransactionTypeDAO extends GenDAO<TransactionType> {

    TransactionType findByTransactionId(int transactionId) throws SQLException;

    // Add other specific methods related to the 'transaction_type' table
}

