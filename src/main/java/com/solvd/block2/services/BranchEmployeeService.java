package com.solvd.block2.services;

import com.solvd.block2.daos.BranchEmployeeDAO;
import com.solvd.block2.models.BranchEmployee;

import java.util.List;

public class BranchEmployeeService {
    private BranchEmployeeDAO branchEmployeeDAO;

    public BranchEmployeeService(BranchEmployeeDAO branchEmployeeDAO) {
        this.branchEmployeeDAO = branchEmployeeDAO;
    }

    public BranchEmployee getBranchEmployeeById(int id) {
        return branchEmployeeDAO.findById(id);
    }

    public List<BranchEmployee> getAllBranchEmployees() {
        return branchEmployeeDAO.findAll();
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

    public List<BranchEmployee> getBranchEmployeesByBranchId(int branchId) {
        return branchEmployeeDAO.findByBranchId(branchId);
    }

    // Add other methods as needed
}

