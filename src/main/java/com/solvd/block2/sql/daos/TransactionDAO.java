package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.ITransactionDAO;
import com.solvd.block2.sql.models.Transaction;
import com.solvd.block2.sql.models.TransactionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.solvd.block2.sql.utilities.DbUtil.getConnection;
import static com.solvd.block2.sql.utilities.DbUtil.releaseConnection;

public class TransactionDAO extends AbstractDAO<Transaction> implements ITransactionDAO {

    private static final Logger LOGGER = LogManager.getLogger(TransactionDAO.class);

    @Override
    protected Transaction createFromResultSet(ResultSet resultSet) throws SQLException {
        int transactionId = resultSet.getInt("transaction_id");
        int accountId = resultSet.getInt("account_id");
        double amount = resultSet.getDouble("amount");
        Date transactionDate = resultSet.getDate("transaction_date");
        int sourceAccountId = resultSet.getInt("source_account_id");
        int destinationAccountId = resultSet.getInt("destination_account_id");

        TransactionType transactionType = getTransactionTypeByTransactionId(transactionId);


        return new Transaction(transactionId, transactionType, accountId, amount, transactionDate, sourceAccountId, destinationAccountId);
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, Transaction entity) throws SQLException {
        statement.setInt(1, entity.getTransactionId());
        statement.setInt(2, entity.getAccountId());
        statement.setDouble(3, entity.getAmount());
        statement.setDate(4, new java.sql.Date(entity.getTransactionDate().getTime()));
        statement.setInt(5, entity.getSourceAccountId());
        statement.setInt(6, entity.getDestinationAccountId());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Transaction entity) throws SQLException {
        statement.setInt(1, entity.getAccountId());
        statement.setDouble(2, entity.getAmount());
        statement.setDate(3, new java.sql.Date(entity.getTransactionDate().getTime()));
        statement.setInt(4, entity.getSourceAccountId());
        statement.setInt(5, entity.getDestinationAccountId());
        statement.setInt(6, entity.getTransactionId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, Transaction entity) throws SQLException {
        statement.setInt(1, entity.getTransactionId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM transactions WHERE transaction_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM transactions";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO transactions (transaction_id, account_id, amount, transaction_date, source_account_id, destination_account_id) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE transactions SET account_id = ?, amount = ?, transaction_date = ?, source_account_id = ?, destination_account_id = ? WHERE transaction_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM transactions WHERE transaction_id = ?";
    }

    public List<Transaction> findByAccountId(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions WHERE account_id = ?");
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = createFromResultSet(resultSet);
                transactions.add(transaction);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return transactions;
    }

    public TransactionType getTransactionTypeByTransactionId(int transactionId) {
        Connection connection = null;
        
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM transaction_type WHERE transaction_type_id = ?");
            statement.setInt(1, transactionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int transactionTypeId = resultSet.getInt("transaction_type_id");
                String transactionTypeName = resultSet.getString("type_name");
                return new TransactionType(transactionTypeId, transactionTypeName);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return null;
    }


    public List<Transaction> findByTransactionTypeId(int transactionTypeId) {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions JOIN transaction_type ON transactions.transaction_id = transaction_type.transaction_id WHERE transaction_type.transaction_type_id = ?");
            statement.setInt(1, transactionTypeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = createFromResultSet(resultSet);
                transactions.add(transaction);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return transactions;
    }
}

