package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.ILoanDAO;
import com.solvd.block2.sql.models.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.block2.sql.utilities.DbUtil.getConnection;
import static com.solvd.block2.sql.utilities.DbUtil.releaseConnection;

public class LoanDAO extends AbstractDAO<Loan> implements ILoanDAO {

    private static final Logger LOGGER = LogManager.getLogger(LoanDAO.class);

    @Override
    protected Loan createFromResultSet(ResultSet resultSet) throws SQLException {
        int loanId = resultSet.getInt("loan_id");
        int customerId = resultSet.getInt("customer_id");
        String loanType = resultSet.getString("loan_type");
        double loanAmount = resultSet.getDouble("loan_amount");
        double interestRate = resultSet.getDouble("interest_rate");
        Date startDate = resultSet.getDate("start_date");
        Date endDate = resultSet.getDate("end_date");

        return new Loan(loanId, customerId, loanType, loanAmount, interestRate, startDate, endDate);
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, Loan loan) throws SQLException {
        statement.setInt(1, loan.getCustomerId());
        statement.setString(2, loan.getLoanType());
        statement.setDouble(3, loan.getLoanAmount());
        statement.setDouble(4, loan.getInterestRate());
        statement.setDate(5, new java.sql.Date(loan.getStartDate().getTime()));
        statement.setDate(6, new java.sql.Date(loan.getEndDate().getTime()));
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Loan loan) throws SQLException {
        statement.setInt(1, loan.getCustomerId());
        statement.setString(2, loan.getLoanType());
        statement.setDouble(3, loan.getLoanAmount());
        statement.setDouble(4, loan.getInterestRate());
        statement.setDate(5, new java.sql.Date(loan.getStartDate().getTime()));
        statement.setDate(6, new java.sql.Date(loan.getEndDate().getTime()));
        statement.setInt(7, loan.getLoanId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, Loan loan) throws SQLException {
        statement.setInt(1, loan.getLoanId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM loans WHERE loan_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM loans WHERE customer_id = ?";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO loans (customer_id, loan_type, loan_amount, interest_rate, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE loans SET customer_id = ?, loan_type = ?, loan_amount = ?, interest_rate = ?, " +
                "start_date = ?, end_date = ? WHERE loan_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM loans WHERE loan_id = ?";
    }

    @Override
    public Loan getById(int loanId) {
        LOGGER.info("Getting loan with ID: {}", loanId);
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindByIdQuery());
            statement.setInt(1, loanId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Error getting loan with ID: {}", loanId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return null;
    }

    @Override
    public List<Loan> getByCustomerId(int customerId) {
        LOGGER.info("Getting loans for customer with ID: {}", customerId);
        List<Loan> loans = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                loans.add(createFromResultSet(resultSet));
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Error getting loans for customer with ID: {}", customerId, e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return loans;
    }

    @Override
    public List<Loan> getAll() {
        LOGGER.info("Getting all loans");
        List<Loan> loans = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                loans.add(createFromResultSet(resultSet));
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Error getting all loans", e);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
        return loans;
    }
}




