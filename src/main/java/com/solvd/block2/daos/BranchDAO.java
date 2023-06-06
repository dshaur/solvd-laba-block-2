package com.solvd.block2.daos;

import com.solvd.block2.interfaces.IBranchDAO;
import com.solvd.block2.models.Branch;
import com.solvd.block2.utilities.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends AbstractDAO<Branch> implements IBranchDAO {

    private static final Logger LOGGER = LogManager.getLogger(BranchDAO.class);

    @Override
    protected Branch createFromResultSet(ResultSet resultSet) throws SQLException {
        int branchId = resultSet.getInt("Branch_ID");
        String branchName = resultSet.getString("Branch_Name");
        String location = resultSet.getString("Location");

        return new Branch(branchId, branchName, location);
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
    public List<Branch> findByLocation(String location) {
        List<Branch> branches = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM branches WHERE Location = ?")) {
            statement.setString(1, location);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Branch branch = createFromResultSet(resultSet);
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branches;
    }


    public List<Branch> findByBranchName(String branchName) {
        List<Branch> branches = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM branches WHERE Branch_Name = ?")) {
            statement.setString(1, branchName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Branch branch = createFromResultSet(resultSet);
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branches;
    }

}



