package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.Transaction;

import java.util.List;

public interface TransactionMapper {
    Transaction getTransactionById(int id);

    List<Transaction> getAllTransactions();

    void insertTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(int id);
    
    List<Transaction> getTransactionsByTransactionTypeId(int transactionTypeId);
}
