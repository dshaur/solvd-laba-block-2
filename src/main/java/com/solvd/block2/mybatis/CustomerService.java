package com.solvd.block2.mybatis;

import com.solvd.block2.mappers.CreditCardMapper;
import com.solvd.block2.mappers.CustomerMapper;
import com.solvd.block2.mappers.DebitCardMapper;
import com.solvd.block2.mappers.LoanMapper;
import com.solvd.block2.sql.models.CreditCard;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.models.DebitCard;
import com.solvd.block2.sql.models.Loan;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class CustomerService {
    private final SqlSessionFactory sqlSessionFactory;

    public CustomerService() {
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

    public void insertCustomer(Customer customer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
            customerMapper.insertCustomer(customer);
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

    public void deleteCustomer(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
            customerMapper.deleteCustomer(id);
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

    public void insertCreditCard(CreditCard creditCard) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);
            creditCardMapper.insertCreditCard(creditCard);
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

    public void deleteCreditCard(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);
            creditCardMapper.deleteCreditCard(id);
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

    public void insertDebitCard(DebitCard debitCard) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DebitCardMapper debitCardMapper = sqlSession.getMapper(DebitCardMapper.class);
            debitCardMapper.insertDebitCard(debitCard);
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

    public void deleteDebitCard(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DebitCardMapper debitCardMapper = sqlSession.getMapper(DebitCardMapper.class);
            debitCardMapper.deleteDebitCard(id);
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

    public void insertLoan(Loan loan) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LoanMapper loanMapper = sqlSession.getMapper(LoanMapper.class);
            loanMapper.insertLoan(loan);
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

    public void deleteLoan(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LoanMapper loanMapper = sqlSession.getMapper(LoanMapper.class);
            loanMapper.deleteLoan(id);
            sqlSession.commit();
        }
    }

}

