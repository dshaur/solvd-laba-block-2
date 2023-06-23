package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.TransactionDAO;
import com.solvd.block2.sql.daos.TransactionTypeDAO;
import com.solvd.block2.sql.models.Transaction;
import com.solvd.block2.sql.models.TransactionType;

import java.sql.SQLException;
import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO;
    private TransactionTypeDAO transactionTypeDAO;

    public TransactionService(TransactionDAO transactionDAO, TransactionTypeDAO transactionTypeDAO) {
        this.transactionDAO = transactionDAO;
        this.transactionTypeDAO = transactionTypeDAO;
    }

    public Transaction getTransactionById(int transactionId) throws SQLException {
        Transaction transaction = transactionDAO.findById(transactionId);
        transaction.setTransactionTypes((TransactionType) transactionTypeDAO.findByTransactionId(transactionId));
        return transaction;
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        return transactionDAO.findAll();
    }

    public void updateTransaction(Transaction transaction) throws SQLException {
        transactionDAO.update(transaction);
    }

    public void deleteTransaction(Transaction transaction) throws SQLException {
        transactionDAO.delete(transaction);
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) throws SQLException {
        return transactionDAO.findByAccountId(accountId);
    }

    public List<Transaction> getTransactionsByTransactionTypeId(int transactionTypeId) throws SQLException {
        return transactionDAO.findByTransactionTypeId(transactionTypeId);
    }

    public List<TransactionType> getAllTransactionTypes() throws SQLException {
        return transactionTypeDAO.findAll();
    }
}




