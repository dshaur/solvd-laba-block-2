package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.ITransactionTypeDAO;
import com.solvd.block2.sql.models.TransactionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.block2.sql.utilities.DbUtil.getConnection;
import static com.solvd.block2.sql.utilities.DbUtil.releaseConnection;

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
        return "INSERT INTO transaction_type (Type_Name) VALUES (?)";
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
    public TransactionType findByTransactionId(int transactionId) throws SQLException {
        TransactionType transactionType = null;
        String query = "SELECT tt.transaction_type_id, tt.type_name " +
                "FROM transaction_type tt " +
                "INNER JOIN transactions tr ON tt.transaction_type_id = tr.transaction_type_id " +
                "WHERE tr.transaction_id = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, transactionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int typeId = resultSet.getInt("transaction_type_id");
                    String typeName = resultSet.getString("type_name");
                    transactionType = new TransactionType(typeId, typeName);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error retrieving transaction type by transaction ID: " + transactionId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return transactionType;
    }
}

