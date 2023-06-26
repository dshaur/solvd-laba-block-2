package com.solvd.block2.mybatis;

import com.solvd.block2.mappers.TransactionMapper;
import com.solvd.block2.mappers.TransactionTypeMapper;
import com.solvd.block2.sql.models.Transaction;
import com.solvd.block2.sql.models.TransactionType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TransactionService {
    private final SqlSessionFactory sqlSessionFactory;

    public TransactionService() {
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

    public void insertTransaction(Transaction transaction) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
            transactionMapper.insertTransaction(transaction);
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

    public void deleteTransaction(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
            transactionMapper.deleteTransaction(id);
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

    public void insertTransactionType(TransactionType transactionType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionTypeMapper transactionTypeMapper = sqlSession.getMapper(TransactionTypeMapper.class);
            transactionTypeMapper.insertTransactionType(transactionType);
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

    public void deleteTransactionType(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TransactionTypeMapper transactionTypeMapper = sqlSession.getMapper(TransactionTypeMapper.class);
            transactionTypeMapper.deleteTransactionType(id);
            sqlSession.commit();
        }
    }
}

