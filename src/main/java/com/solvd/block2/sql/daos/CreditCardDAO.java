package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.ICreditCardDAO;
import com.solvd.block2.sql.models.CreditCard;
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

public class CreditCardDAO extends AbstractDAO<CreditCard> implements ICreditCardDAO {

    private static final Logger LOGGER = LogManager.getLogger(CreditCardDAO.class);

    @Override
    protected CreditCard createFromResultSet(ResultSet resultSet) throws SQLException {
        int creditCardId = resultSet.getInt("credit_card_id");
        int customerId = resultSet.getInt("customer_id");
        String creditCardNumber = resultSet.getString("credit_card_number");
        Date expiryDate = resultSet.getDate("expiry_date");
        double creditLimit = resultSet.getDouble("credit_limit");
        double outstandingBalance = resultSet.getDouble("outstanding_balance");

        return new CreditCard(creditCardId, customerId, creditCardNumber, expiryDate, creditLimit, outstandingBalance);
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, CreditCard creditCard) throws SQLException {
        statement.setInt(1, creditCard.getCustomerId());
        statement.setString(2, creditCard.getCreditCardNumber());
        statement.setDate(3, new java.sql.Date(creditCard.getExpiryDate().getTime()));
        statement.setDouble(4, creditCard.getCreditLimit());
        statement.setDouble(5, creditCard.getOutstandingBalance());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, CreditCard creditCard) throws SQLException {
        statement.setInt(1, creditCard.getCustomerId());
        statement.setString(2, creditCard.getCreditCardNumber());
        statement.setDate(3, new java.sql.Date(creditCard.getExpiryDate().getTime()));
        statement.setDouble(4, creditCard.getCreditLimit());
        statement.setDouble(5, creditCard.getOutstandingBalance());
        statement.setInt(6, creditCard.getCreditCardId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, CreditCard creditCard) throws SQLException {
        statement.setInt(1, creditCard.getCreditCardId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM credit_cards WHERE credit_card_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM credit_cards WHERE customer_id = ?";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO credit_cards (customer_id, credit_card_number, expiry_date, credit_limit, outstanding_balance) " +
                "VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE credit_cards SET customer_id = ?, credit_card_number = ?, expiry_date = ?, credit_limit = ?, " +
                "outstanding_balance = ? WHERE credit_card_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM credit_cards WHERE credit_card_id = ?";
    }

    @Override
    public CreditCard getById(int creditCardId) throws SQLException {
        LOGGER.info("Getting credit card with ID: {}", creditCardId);
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindByIdQuery());
            statement.setInt(1, creditCardId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting credit card with ID: {}", creditCardId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }

        return null;
    }

    @Override
    public List<CreditCard> getByCustomerId(int customerId) throws SQLException {
        LOGGER.info("Getting credit cards for customer with ID: {}", customerId);
        List<CreditCard> creditCards = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                creditCards.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting credit cards for customer with ID: {}", customerId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return creditCards;
    }

    @Override
    public List<CreditCard> getAll() throws SQLException {
        LOGGER.info("Getting all credit cards");
        List<CreditCard> creditCards = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                creditCards.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting all credit cards", e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }

        return creditCards;
    }
}




