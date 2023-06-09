package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.TransactionDAO;
import com.solvd.block2.sql.daos.TransactionTypeDAO;
import com.solvd.block2.sql.models.Transaction;
import com.solvd.block2.sql.models.TransactionType;

import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO;
    private TransactionTypeDAO transactionTypeDAO;

    public TransactionService(TransactionDAO transactionDAO, TransactionTypeDAO transactionTypeDAO) {
        this.transactionDAO = transactionDAO;
        this.transactionTypeDAO = transactionTypeDAO;
    }

    public Transaction getTransactionById(int transactionId) {
        return transactionDAO.findById(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDAO.findAll();
    }

    public void updateTransaction(Transaction transaction) {
        transactionDAO.update(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        transactionDAO.delete(transaction);
        List<TransactionType> transactionTypes = transaction.getTransactionTypes();
        for (TransactionType transactionType : transactionTypes) {
            transactionTypeDAO.delete(transactionType);
        }
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) {
        return transactionDAO.findByAccountId(accountId);
    }

    // Additional methods if needed

    public List<Transaction> getTransactionsByTransactionTypeId(int transactionTypeId) {
        return transactionDAO.findByTransactionTypeId(transactionTypeId);
    }

    public List<TransactionType> getAllTransactionTypes() {
        return transactionTypeDAO.findAll();
    }

    public void createTransactionType(TransactionType transactionType) {
        transactionTypeDAO.create(transactionType);
    }

    public void updateTransactionType(TransactionType transactionType) {
        transactionTypeDAO.update(transactionType);
    }

    public void deleteTransactionType(TransactionType transactionType) {
        transactionTypeDAO.delete(transactionType);
    }
}


