package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.IBranchEmployeeDAO;
import com.solvd.block2.sql.models.BranchEmployee;
import com.solvd.block2.sql.utilities.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchEmployeeDAO extends AbstractDAO<BranchEmployee> implements IBranchEmployeeDAO {

    private static final Logger LOGGER = LogManager.getLogger(BranchEmployeeDAO.class);

    @Override
    protected BranchEmployee createFromResultSet(ResultSet resultSet) throws SQLException {
        int employeeId = resultSet.getInt("employee_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int branchId = resultSet.getInt("branch_id");
        String position = resultSet.getString("position");

        return new BranchEmployee(employeeId, firstName, lastName, branchId, position);
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, BranchEmployee entity) throws SQLException {
        statement.setInt(1, entity.getEmployeeId());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setInt(4, entity.getBranchId());
        statement.setString(5, entity.getPosition());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, BranchEmployee entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setInt(3, entity.getBranchId());
        statement.setString(4, entity.getPosition());
        statement.setInt(5, entity.getEmployeeId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, BranchEmployee entity) throws SQLException {
        statement.setInt(1, entity.getEmployeeId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM branch_employees WHERE employee_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM branch_employees";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO branch_employees (employee_id, first_name, last_name, branch_id, position) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE branch_employees SET first_name = ?, last_name = ?, branch_id = ?, position = ? WHERE employee_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM branch_employees WHERE employee_id = ?";
    }

    public List<BranchEmployee> findByBranchId(int branchId) throws SQLException {
        List<BranchEmployee> branchEmployees = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM branch_employees WHERE branch_id = ?");
            statement.setInt(1, branchId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BranchEmployee branchEmployee = createFromResultSet(resultSet);
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

    public BranchEmployee findByEmployeeId(int employeeId) throws SQLException {
        Connection connection = null;
        BranchEmployee branchEmployee = null;

        try {
            connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM branch_employees WHERE employee_id = ?");
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                branchEmployee = createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DbUtil.releaseConnection(connection);
            }
        }

        return branchEmployee;
    }
    

    // Add other specific methods related to the 'branch_employees' table if needed
}
