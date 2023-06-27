package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.IBranchDAO;
import com.solvd.block2.sql.models.Account;
import com.solvd.block2.sql.models.Branch;
import com.solvd.block2.sql.models.BranchEmployee;
import com.solvd.block2.sql.utilities.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends AbstractDAO<Branch> implements IBranchDAO {

    private static final Logger LOGGER = LogManager.getLogger(BranchDAO.class);

    @Override
    protected Branch createFromResultSet(ResultSet resultSet) throws SQLException {
        int branchId = resultSet.getInt("Branch_ID");
        String branchName = resultSet.getString("Branch_Name");
        String location = resultSet.getString("Location");

        List<BranchEmployee> branchEmployees = getBranchEmployeesByBranchId(branchId);

        List<Account> accounts = getAccountsByBranchId(branchId);

        return new Branch(branchId, branchName, location, branchEmployees, accounts);
    }

    private List<Account> getAccountsByBranchId(int branchId) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DbUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM accounts WHERE Branch_ID = ?");
            statement.setInt(1, branchId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                String accountType = resultSet.getString("account_type");
                double balance = resultSet.getDouble("balance");
                java.sql.Date openDate = resultSet.getDate("open_date");
                Date lastTransactionDate = resultSet.getDate("last_transaction_date");
                branchId = resultSet.getInt("branch_id");

                // Add other required account properties

                Account account = new Account(accountId, accountType, balance, openDate, lastTransactionDate, branchId);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.releaseConnection(connection);
        }

        return accounts;
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, Branch entity) throws SQLException {
        statement.setInt(1, entity.getBranchId());
        statement.setString(2, entity.getBranchName());
        statement.setString(3, entity.getLocation());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Branch entity) throws SQLException {
        statement.setString(1, entity.getBranchName());
        statement.setString(2, entity.getLocation());
        statement.setInt(3, entity.getBranchId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, Branch entity) throws SQLException {
        statement.setInt(1, entity.getBranchId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM branches WHERE Branch_ID = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM branches";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO branches (Branch_ID, Branch_Name, Location) VALUES (?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE branches SET Branch_Name = ?, Location = ? WHERE Branch_ID = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM branches WHERE Branch_ID = ?";
    }

    @Override
    public List<Branch> findByLocation(String location) throws SQLException {
        List<Branch> branches = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM branches WHERE Location = ?");
            statement.setString(1, location);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Branch branch = createFromResultSet(resultSet);
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection);
            }
        }

        return branches;
    }


    public List<Branch> findByBranchName(String branchName) throws SQLException {
        List<Branch> branches = new ArrayList<>();
        Connection connection = null; // Declare connection outside the try-with-resources block

        try {
            connection = DbUtil.getConnection(); // Get a connection from the connection pool
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM branches WHERE Branch_Name = ?");
            statement.setString(1, branchName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Branch branch = createFromResultSet(resultSet);
                branches.add(branch);
            }
        } catch (SQLException e) { // Handle InterruptedException when calling getConnection
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection); // Release the connection back to the pool
            }
        }

        return branches;
    }


    public List<BranchEmployee> getBranchEmployeesByBranchId(int branchId) throws SQLException {
        List<BranchEmployee> branchEmployees = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM branch_employees WHERE Branch_ID = ?");
            statement.setInt(1, branchId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String position = resultSet.getString("position");

                BranchEmployee branchEmployee = new BranchEmployee(employeeId, firstName, lastName, branchId, position);
                branchEmployees.add(branchEmployee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection);
            }
        }

        return branchEmployees;
    }

}



