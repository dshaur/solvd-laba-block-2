package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.Transaction;

import java.util.List;

public interface ITransactionDAO extends GenDAO<Transaction> {
    List<Transaction> findByAccountId(int accountId);
}

