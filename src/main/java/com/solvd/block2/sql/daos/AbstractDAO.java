package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.GenDAO;
import com.solvd.block2.sql.utilities.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDAO<T> implements GenDAO<T> {

    protected abstract T createFromResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public T findById(int id) throws SQLException {
        Connection connection = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindByIdQuery());
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection);
            }
        }

        return null;
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> entities = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T entity = createFromResultSet(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection);
            }
        }

        return entities;
    }

    @Override
    public void create(T entity) throws SQLException {
        Connection connection = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(getCreateQuery());
            setCreateStatementParameters(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(T entity) throws SQLException {
        Connection connection = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(getUpdateQuery());
            setUpdateStatementParameters(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(T entity) throws SQLException {
        Connection connection = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(getDeleteQuery());
            setDeleteStatementParameters(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection);
            }
        }
    }

    // Utility methods for statement parameters

    protected abstract void setCreateStatementParameters(PreparedStatement statement, T entity) throws SQLException;

    protected abstract void setUpdateStatementParameters(PreparedStatement statement, T entity) throws SQLException;

    protected abstract void setDeleteStatementParameters(PreparedStatement statement, T entity) throws SQLException;

    // Utility methods for query strings

    protected abstract String getFindByIdQuery();

    protected abstract String getFindAllQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    // Add other utility methods as needed

}


