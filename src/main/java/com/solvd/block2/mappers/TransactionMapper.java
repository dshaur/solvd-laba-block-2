package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.Transaction;

import java.util.List;

public interface TransactionMapper {
    Transaction getTransactionById(int id);

    List<Transaction> getAllTransactions();

    void createTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);

    List<Transaction> getTransactionsByTransactionTypeId(int transactionTypeId);
}
