package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.BranchDAO;
import com.solvd.block2.sql.daos.BranchEmployeeDAO;
import com.solvd.block2.sql.models.Branch;
import com.solvd.block2.sql.models.BranchEmployee;

import java.sql.SQLException;
import java.util.List;

public class BranchService {
    private BranchDAO branchDAO;
    private BranchEmployeeDAO branchEmployeeDAO;

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

    public void saveBranch(Branch branch) throws SQLException {
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

    public void saveBranchEmployee(BranchEmployee branchEmployee) throws SQLException {
        branchEmployeeDAO.create(branchEmployee);
    }

    public void updateBranchEmployee(BranchEmployee branchEmployee) throws SQLException {
        branchEmployeeDAO.update(branchEmployee);
    }

    public void deleteBranchEmployee(BranchEmployee branchEmployee) throws SQLException {
        branchEmployeeDAO.delete(branchEmployee);
    }

    // Add other methods as needed
}




