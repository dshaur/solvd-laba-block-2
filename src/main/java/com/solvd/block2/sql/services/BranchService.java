package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.BranchDAO;
import com.solvd.block2.sql.daos.BranchEmployeeDAO;
import com.solvd.block2.sql.models.Branch;
import com.solvd.block2.sql.models.BranchEmployee;

import java.sql.SQLException;
import java.util.List;

public class BranchService implements IBranchService {
    private BranchDAO branchDAO;
    private BranchEmployeeDAO branchEmployeeDAO;

    public BranchService() {
        // No argument constructor
    }

    public BranchService(BranchDAO branchDAO, BranchEmployeeDAO branchEmployeeDAO) {
        this.branchDAO = branchDAO;
        this.branchEmployeeDAO = branchEmployeeDAO;
    }

    public Branch getBranchById(int id) throws SQLException {
        Branch branch = branchDAO.findById(id);
        branch.setBranchEmployees(branchEmployeeDAO.findByBranchId(id));
        return branch;
    }

    public List<Branch> getAllBranches() throws SQLException {
        return branchDAO.findAll();
    }

    public void createBranch(Branch branch) throws SQLException {
        branchDAO.create(branch);
    }

    public void updateBranch(Branch branch) throws SQLException {
        branchDAO.update(branch);
    }

    public void deleteBranch(Branch branch) throws SQLException {
        branchDAO.delete(branch);
    }

    public List<Branch> getBranchesByLocation(String location) throws SQLException {
        return branchDAO.findByLocation(location);
    }

    public List<Branch> getBranchesByBranchName(String branchName) throws SQLException {
        return branchDAO.findByBranchName(branchName);
    }

    public List<BranchEmployee> getBranchEmployeesByBranchId(int branchId) throws SQLException {
        return branchEmployeeDAO.findByBranchId(branchId);
    }

    @Override
    public void createBranchEmployee(BranchEmployee branchEmployee) throws SQLException {
        branchEmployeeDAO.create(branchEmployee);
    }

    @Override
    public void updateBranchEmployee(BranchEmployee branchEmployee) throws SQLException {
        branchEmployeeDAO.update(branchEmployee);
    }

    @Override
    public void deleteBranchEmployee(BranchEmployee branchEmployee) throws SQLException {
        branchEmployeeDAO.delete(branchEmployee);
    }

    public List<BranchEmployee> getAllBranchEmployees() throws SQLException {
        return branchEmployeeDAO.findAll();
    }

    public BranchEmployee getBranchEmployeesById(int employeeId) throws SQLException {
        return branchEmployeeDAO.findByEmployeeId(employeeId);
    }
    // Add other methods as needed
}




