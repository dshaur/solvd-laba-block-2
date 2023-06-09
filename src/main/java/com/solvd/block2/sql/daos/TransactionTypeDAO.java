package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.ITransactionTypeDAO;
import com.solvd.block2.sql.models.TransactionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.block2.sql.utilities.DbUtil.getConnection;

public class TransactionTypeDAO extends AbstractDAO<TransactionType> implements ITransactionTypeDAO {

    private static final Logger LOGGER = LogManager.getLogger(TransactionTypeDAO.class);

    @Override
    protected TransactionType createFromResultSet(ResultSet resultSet) throws SQLException {
        int typeId = resultSet.getInt("Transaction_Type_ID");
        String typeName = resultSet.getString("Type_Name");

        return new TransactionType(typeId, typeName);
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, TransactionType entity) throws SQLException {
        statement.setInt(1, entity.getTypeId());
        statement.setString(2, entity.getTypeName());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, TransactionType entity) throws SQLException {
        statement.setString(1, entity.getTypeName());
        statement.setInt(2, entity.getTypeId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, TransactionType entity) throws SQLException {
        statement.setInt(1, entity.getTypeId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM transaction_type WHERE Type_ID = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM transaction_type";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO transaction_type (Type_ID, Type_Name) VALUES (?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE transaction_type SET Type_Name = ? WHERE Type_ID = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM transaction_type WHERE Type_ID = ?";
    }

    @Override
    public List<TransactionType> findByTransactionId(int transactionId) {
        List<TransactionType> transactionTypes = new ArrayList<>();

        String query = "SELECT tt.Type_ID, tt.Type_Name FROM transaction_type tt " +
                "INNER JOIN transaction tr ON tt.Type_ID = tr.Type_ID " +
                "WHERE tr.Transaction_ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, transactionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int typeId = resultSet.getInt("Type_ID");
                    String typeName = resultSet.getString("Type_Name");
                    TransactionType transactionType = new TransactionType(typeId, typeName);
                    transactionTypes.add(transactionType);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error retrieving transaction types by transaction ID: " + transactionId, e);
        }

        return transactionTypes;
    }


    // Add other specific methods related to the 'transaction_type' table
}

