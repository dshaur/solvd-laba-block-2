package com.solvd.block2.sql.services;

import com.solvd.block2.mappers.*;
import com.solvd.block2.mybatis.MyBatisSessionFactory;
import com.solvd.block2.sql.models.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

public class MyBatisCustomerService implements ICustomerService {
    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisCustomerService() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }


    // Service Methods for Customer
    public Customer getCustomerById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
            return customerMapper.getCustomerById(id);
        }
    }

    public List<Customer> getAllCustomers() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
            return customerMapper.getAllCustomers();
        }
    }

    public void createCustomer(Customer customer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
            customerMapper.createCustomer(customer);
            sqlSession.commit();
        }
    }

    public void updateCustomer(Customer customer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
            customerMapper.updateCustomer(customer);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteCustomer(Customer customer) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
            customerMapper.deleteCustomer(customer);
            sqlSession.commit();
        }
    }


    // Service Methods for Credit Card
    public CreditCard getCreditCardById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);
            return creditCardMapper.getCreditCardById(id);
        }
    }

    public List<CreditCard> getCreditCardsByCustomerId(int customerId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);
            return creditCardMapper.getCreditCardsByCustomerId(customerId);
        }
    }

    public void createCreditCard(CreditCard creditCard) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);
            creditCardMapper.createCreditCard(creditCard);
            sqlSession.commit();
        }
    }

    public void updateCreditCard(CreditCard creditCard) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);
            creditCardMapper.updateCreditCard(creditCard);
            sqlSession.commit();
        }
    }

    // Service Methods for Debit Card
    public DebitCard getDebitCardById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DebitCardMapper debitCardMapper = sqlSession.getMapper(DebitCardMapper.class);
            return debitCardMapper.getDebitCardById(id);
        }
    }

    public List<DebitCard> getDebitCardsByCustomerId(int customerId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DebitCardMapper debitCardMapper = sqlSession.getMapper(DebitCardMapper.class);
            return debitCardMapper.getDebitCardsByCustomerId(customerId);
        }
    }

    public void createDebitCard(DebitCard debitCard) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DebitCardMapper debitCardMapper = sqlSession.getMapper(DebitCardMapper.class);
            debitCardMapper.createDebitCard(debitCard);
            sqlSession.commit();
        }
    }

    public void updateDebitCard(DebitCard debitCard) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DebitCardMapper debitCardMapper = sqlSession.getMapper(DebitCardMapper.class);
            debitCardMapper.updateDebitCard(debitCard);
            sqlSession.commit();
        }
    }

    // Service Methods for Loan
    public Loan getLoanById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LoanMapper loanMapper = sqlSession.getMapper(LoanMapper.class);
            return loanMapper.getLoanById(id);
        }
    }

    public List<Loan> getLoansByCustomerId(int customerId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LoanMapper loanMapper = sqlSession.getMapper(LoanMapper.class);
            return loanMapper.getLoansByCustomerId(customerId);
        }
    }

    public void createLoan(Loan loan) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LoanMapper loanMapper = sqlSession.getMapper(LoanMapper.class);
            loanMapper.createLoan(loan);
            sqlSession.commit();
        }
    }

    public void updateLoan(Loan loan) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LoanMapper loanMapper = sqlSession.getMapper(LoanMapper.class);
            loanMapper.updateLoan(loan);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteCreditCard(CreditCard creditCard) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);
            creditCardMapper.deleteCreditCard(creditCard);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteDebitCard(DebitCard debitCard) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DebitCardMapper debitCardMapper = sqlSession.getMapper(DebitCardMapper.class);
            debitCardMapper.deleteDebitCard(debitCard);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteLoan(Loan loan) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LoanMapper loanMapper = sqlSession.getMapper(LoanMapper.class);
            loanMapper.deleteLoan(loan);
            sqlSession.commit();
        }
    }

    @Override
    public List<Account> getAccountsByCustomerId(int id) throws SQLException {
        return null;
    }

    @Override
    public void createAccount(Account account) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.createAccount(account);
            sqlSession.commit();
        }
    }

    @Override
    public void updateAccount(Account account) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.updateAccount(account);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAccount(Account account) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.deleteAccount(account);
            sqlSession.commit();
        }
    }


}

