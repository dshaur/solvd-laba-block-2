package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.IDebitCardDAO;
import com.solvd.block2.sql.models.DebitCard;
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

public class DebitCardDAO extends AbstractDAO<DebitCard> implements IDebitCardDAO {

    private static final Logger LOGGER = LogManager.getLogger(DebitCardDAO.class);

    @Override
    protected DebitCard createFromResultSet(ResultSet resultSet) throws SQLException {
        int debitCardId = resultSet.getInt("Debit_Card_ID");
        int customerId = resultSet.getInt("Customer_ID");
        String cardNumber = resultSet.getString("Debit_Card_Number");
        Date expirationDate = resultSet.getDate("Expiry_Date");

        return new DebitCard(debitCardId, customerId, cardNumber, expirationDate);
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, DebitCard debitCard) throws SQLException {
        statement.setInt(1, debitCard.getCardId());
        statement.setInt(2, debitCard.getCustomerId());
        statement.setString(3, debitCard.getCardNumber());
        statement.setDate(4, (java.sql.Date) debitCard.getExpirationDate());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, DebitCard debitCard) throws SQLException {
        statement.setInt(1, debitCard.getCustomerId());
        statement.setString(2, debitCard.getCardNumber());
        statement.setDate(3, (java.sql.Date) debitCard.getExpirationDate());
        statement.setInt(4, debitCard.getCardId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, DebitCard debitCard) throws SQLException {
        statement.setInt(1, debitCard.getCardId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM debit_cards WHERE Debit_Card_ID = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM debit_cards";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO debit_cards (Debit_Card_ID, Customer_ID, Debit_Card_Number, Expiry_Date) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE debit_cards SET Customer_ID = ?, Debit_Card_Number = ?, Expiry_Date = ? WHERE Debit_Card_ID = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM debit_cards WHERE Debit_Card_ID = ?";
    }

    @Override
    public DebitCard getById(int debitCardId) {
        LOGGER.info("Getting debit card with ID: {}", debitCardId);
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindByIdQuery());
            statement.setInt(1, debitCardId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Error getting debit card with ID: {}", debitCardId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return null;
    }

    @Override
    public List<DebitCard> getByCustomerId(int customerId) {
        LOGGER.info("Getting debit cards for customer with ID: {}", customerId);
        List<DebitCard> debitCards = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM debit_cards WHERE Customer_ID = ?");
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                debitCards.add(createFromResultSet(resultSet));
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Error getting debit cards for customer with ID: {}", customerId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }

        return debitCards;
    }

    @Override
    public List<DebitCard> getAll() {
        LOGGER.info("Getting all debit cards");
        List<DebitCard> debitCards = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                debitCards.add(createFromResultSet(resultSet));
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Error getting all debit cards", e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }

        return debitCards;
    }
}

