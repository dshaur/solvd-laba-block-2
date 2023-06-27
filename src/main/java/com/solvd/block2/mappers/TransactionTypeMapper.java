package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.TransactionType;

import java.util.List;

public interface TransactionTypeMapper {
    TransactionType getTransactionTypeById(int id);

    List<TransactionType> getAllTransactionTypes();

    void createTransactionType(TransactionType transactionType);

    void updateTransactionType(TransactionType transactionType);

    void deleteTransactionType(TransactionType transactionType);

}
