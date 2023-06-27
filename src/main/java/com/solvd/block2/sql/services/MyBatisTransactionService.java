package com.solvd.block2.sql.services;

import com.solvd.block2.mappers.TransactionMapper;
import com.solvd.block2.mappers.TransactionTypeMapper;
import com.solvd.block2.mybatis.MyBatisSessionFactory;
import com.solvd.block2.sql.models.Transaction;
import com.solvd.block2.sql.models.TransactionType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisTransactionService implements ITransactionService {
    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisTransactionService() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }

    // Service Methods for Transaction
    public Transaction getTransactionById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
            return transactionMapper.getTransactionById(id);
        }
    }

    public List<Transaction> getAllTransactions() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
            return transactionMapper.getAllTransactions();
        }
    }

    public List<Transaction> getTransactionsByTransactionTypeId(int transactionTypeId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
            return transactionMapper.getTransactionsByTransactionTypeId(transactionTypeId);
        }
    }

    public void createTransaction(Transaction transaction) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
            transactionMapper.createTransaction(transaction);
            sqlSession.commit();
        }
    }

    public void updateTransaction(Transaction transaction) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
            transactionMapper.updateTransaction(transaction);
            sqlSession.commit();
        }
    }

    public void deleteTransaction(Transaction transaction) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
            transactionMapper.deleteTransaction(transaction);
            sqlSession.commit();
        }
    }

    // Service Methods for TransactionType
    public TransactionType getTransactionTypeById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionTypeMapper transactionTypeMapper = sqlSession.getMapper(TransactionTypeMapper.class);
            return transactionTypeMapper.getTransactionTypeById(id);
        }
    }

    public List<TransactionType> getAllTransactionTypes() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionTypeMapper transactionTypeMapper = sqlSession.getMapper(TransactionTypeMapper.class);
            return transactionTypeMapper.getAllTransactionTypes();
        }
    }

    public void createTransactionType(TransactionType transactionType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionTypeMapper transactionTypeMapper = sqlSession.getMapper(TransactionTypeMapper.class);
            transactionTypeMapper.createTransactionType(transactionType);
            sqlSession.commit();
        }
    }

    public void updateTransactionType(TransactionType transactionType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionTypeMapper transactionTypeMapper = sqlSession.getMapper(TransactionTypeMapper.class);
            transactionTypeMapper.updateTransactionType(transactionType);
            sqlSession.commit();
        }
    }

    public void deleteTransactionType(TransactionType transactionType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionTypeMapper transactionTypeMapper = sqlSession.getMapper(TransactionTypeMapper.class);
            transactionTypeMapper.deleteTransactionType(transactionType);
            sqlSession.commit();
        }
    }
}

