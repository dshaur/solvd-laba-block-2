package com.solvd.block2.sql.services;


import com.solvd.block2.mappers.AccountMapper;
import com.solvd.block2.mybatis.MyBatisSessionFactory;
import com.solvd.block2.sql.models.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisAccountService implements IAccountService {
    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisAccountService() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }


    public Account getAccountById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            return accountMapper.getAccountById(id);
        }
    }

    public List<Account> getAllAccounts() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            return accountMapper.getAllAccounts();
        }
    }

    public void createAccount(Account account) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.createAccount(account);
            sqlSession.commit();
        }
    }

    public void updateAccount(Account account) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.updateAccount(account);
            sqlSession.commit();
        }
    }

    public void deleteAccount(Account account) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.deleteAccount(account);
            sqlSession.commit();
        }
    }
}

