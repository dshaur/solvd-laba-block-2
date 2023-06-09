package com.solvd.block2.sql.services;

import com.solvd.block2.sql.daos.BranchDAO;
import com.solvd.block2.sql.daos.BranchEmployeeDAO;
import com.solvd.block2.sql.models.Branch;
import com.solvd.block2.sql.models.BranchEmployee;

import java.util.List;

public class BranchService {
    private BranchEmployeeDAO branchEmployeeDAO;
    private BranchDAO branchDAO;

    public BranchService(BranchDAO branchDAO, BranchEmployeeDAO branchEmployeeDAO) {
        this.branchDAO = branchDAO;
        this.branchEmployeeDAO = branchEmployeeDAO;
    }

    public Branch getBranchById(int id) {
        return branchDAO.findById(id);
    }

    public List<Branch> getAllBranches() {
        return branchDAO.findAll();
    }

    public void saveBranch(Branch branch) {
        branchDAO.create(branch);
    }

    public void updateBranch(Branch branch) {
        branchDAO.update(branch);
    }

    public void deleteBranch(Branch branch) {
        branchDAO.delete(branch);
    }

    public List<Branch> getBranchesByLocation(String location) {
        return branchDAO.findByLocation(location);
    }

    public List<Branch> getBranchesByBranchName(String branchName) {
        return branchDAO.findByBranchName(branchName);
    }

    public List<BranchEmployee> getBranchEmployeesByBranchId(int branchId) {
        return branchDAO.getBranchEmployeesByBranchId(branchId);
    }

    public void saveBranchEmployee(BranchEmployee branchEmployee) {
        branchEmployeeDAO.create(branchEmployee);
    }

    public void updateBranchEmployee(BranchEmployee branchEmployee) {
        branchEmployeeDAO.update(branchEmployee);
    }

    public void deleteBranchEmployee(BranchEmployee branchEmployee) {
        branchEmployeeDAO.delete(branchEmployee);
    }

    // Add other methods as needed
}



