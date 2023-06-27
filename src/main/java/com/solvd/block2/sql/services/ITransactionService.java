package com.solvd.block2.sql.services;

import com.solvd.block2.sql.models.Transaction;
import com.solvd.block2.sql.models.TransactionType;

import java.sql.SQLException;
import java.util.List;

public interface ITransactionService {
    Transaction getTransactionById(int id) throws SQLException;

    List<Transaction> getAllTransactions() throws SQLException;

    List<Transaction> getTransactionsByTransactionTypeId(int transactionTypeId) throws SQLException;

    void createTransaction(Transaction transaction) throws SQLException;

    void updateTransaction(Transaction transaction) throws SQLException;

    void deleteTransaction(Transaction transaction) throws SQLException;

    TransactionType getTransactionTypeById(int id) throws SQLException;

    List<TransactionType> getAllTransactionTypes() throws SQLException;

    void createTransactionType(TransactionType transactionType) throws SQLException;

    void updateTransactionType(TransactionType transactionType) throws SQLException;

    void deleteTransactionType(TransactionType transactionType) throws SQLException;
}
