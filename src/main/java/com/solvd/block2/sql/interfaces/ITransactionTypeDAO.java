package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.TransactionType;

import java.util.List;

public interface ITransactionTypeDAO extends GenDAO<TransactionType> {

    List<TransactionType> findByTransactionId(int transactionId);

    // Add other specific methods related to the 'transaction_type' table
}

