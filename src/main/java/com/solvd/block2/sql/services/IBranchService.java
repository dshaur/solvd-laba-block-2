package com.solvd.block2.sql.services;

import com.solvd.block2.sql.models.Branch;
import com.solvd.block2.sql.models.BranchEmployee;

import java.sql.SQLException;
import java.util.List;

public interface IBranchService {
    Branch getBranchById(int id) throws SQLException;

    List<Branch> getAllBranches() throws SQLException;

    BranchEmployee getBranchEmployeesById(int id) throws SQLException;

    List<BranchEmployee> getAllBranchEmployees() throws SQLException;

    void createBranch(Branch branch) throws SQLException;

    void updateBranch(Branch branch) throws SQLException;

    void deleteBranch(Branch branch) throws SQLException;

    void createBranchEmployee(BranchEmployee branchEmployee) throws SQLException;

    void updateBranchEmployee(BranchEmployee branchEmployee) throws SQLException;

    void deleteBranchEmployee(BranchEmployee branchEmployee) throws SQLException;
}
