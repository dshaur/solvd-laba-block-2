package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.BranchEmployee;

import java.sql.SQLException;
import java.util.List;

public interface IBranchEmployeeDAO extends GenDAO<BranchEmployee> {

    List<BranchEmployee> findByBranchId(int branchId) throws SQLException;

    // Add other specific methods related to the 'branch_employees' table
}