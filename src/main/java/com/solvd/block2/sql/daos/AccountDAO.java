package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.IAccountDAO;
import com.solvd.block2.sql.models.Account;
import com.solvd.block2.sql.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.block2.sql.utilities.DbUtil.getConnection;
import static com.solvd.block2.sql.utilities.DbUtil.releaseConnection;

public class AccountDAO extends AbstractDAO<Account> implements IAccountDAO {

    private static final Logger LOGGER = LogManager.getLogger(AccountDAO.class);

    private CustomerDAO customerDAO;

    public AccountDAO() {
        this.customerDAO = customerDAO;
    }


    @Override
    protected Account createFromResultSet(ResultSet resultSet) throws SQLException {
        int accountId = resultSet.getInt("Account_ID");
        String accountType = resultSet.getString("Account_Type");
        double balance = resultSet.getDouble("Balance");
        Date openDate = resultSet.getDate("Open_Date");
        Date lastTransactionDate = resultSet.getDate("Last_Transaction_Date");
        int branchId = resultSet.getInt("Branch_ID");


        List<Customer> customers = getCustomersByAccountId(accountId);

        return new Account(accountId, accountType, balance, openDate, lastTransactionDate, branchId, customers);
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, Account account) throws SQLException {
        statement.setInt(1, account.getAccountId());
        statement.setString(2, account.getAccountType());
        statement.setDouble(3, account.getBalance());
        statement.setDate(4, account.getOpenDate());
        statement.setDate(5, account.getLastTransactionDate());
        statement.setInt(6, account.getBranchId());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Account account) throws SQLException {
        statement.setString(1, account.getAccountType());
        statement.setDouble(2, account.getBalance());
        statement.setDate(3, account.getOpenDate());
        statement.setDate(4, account.getLastTransactionDate());
        statement.setInt(5, account.getBranchId());
        statement.setInt(6, account.getAccountId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, Account account) throws SQLException {
        statement.setInt(1, account.getAccountId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM accounts WHERE Account_ID = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM accounts";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO accounts (Account_ID, Account_Type, Balance, Open_Date, Last_Transaction_Date, Branch_ID) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE accounts SET Account_Type = ?, Balance = ?, Open_Date = ?, Last_Transaction_Date = ?, Branch_ID = ? WHERE Account_ID = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM accounts WHERE Account_ID = ?";
    }


    @Override
    public Account getAccountById(int accountId) throws SQLException {
        LOGGER.info("Getting account with ID: {}", accountId);
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindByIdQuery());
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting account with ID: {}", accountId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }

        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(int accountId) {

    }

    @Override
    public List<Account> getAllAccounts() throws SQLException {
        LOGGER.info("Getting all accounts");
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting all accounts", e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return accounts;
    }

    @Override
    public List<Account> getByCustomerId(int customerId) throws SQLException {
        LOGGER.info("Getting accounts for customer with ID: {}", customerId);
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer_accounts WHERE Customer_ID = ?");
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int accountId = resultSet.getInt("Account_ID");
                Account account = getAccountById(accountId);
                if (account != null) {
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting accounts for customer with ID: {}", customerId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }

        return accounts;
    }

    public List<Customer> getCustomersByAccountId(int accountId) throws SQLException {
        LOGGER.info("Getting customers for account with ID: {}", accountId);
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer_accounts WHERE Account_ID = ?");
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int customerId = resultSet.getInt("Customer_ID");
                Customer customer = customerDAO.getById(customerId);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting customers for account with ID: {}", accountId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }

        return customers;
    }

    @Override
    public List<Account> getAccountsByBranchId(int branchId) throws SQLException {
        LOGGER.info("Getting accounts for branch with ID: {}", branchId);
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE Branch_ID = ?");
            statement.setInt(1, branchId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting accounts for branch with ID: {}", branchId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }

        return accounts;
    }


}

