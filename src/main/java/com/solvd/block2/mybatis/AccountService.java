package com.solvd.block2.mybatis;


import com.solvd.block2.mappers.AccountMapper;
import com.solvd.block2.sql.models.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class AccountService {
    private final SqlSessionFactory sqlSessionFactory;

    public AccountService() {
        this.sqlSessionFactory = createSqlSessionFactory();
    }

    // Create session factory
    private SqlSessionFactory createSqlSessionFactory() {
        String resource = "mybatis/mybatis_config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create SqlSessionFactory.", e);
        }
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

    public void insertAccount(Account account) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.insertAccount(account);
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

    public void deleteAccount(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.deleteAccount(id);
            sqlSession.commit();
        }
    }
}
