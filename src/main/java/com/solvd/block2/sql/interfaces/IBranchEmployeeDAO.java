package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.BranchEmployee;

import java.util.List;

public interface IBranchEmployeeDAO extends GenDAO<BranchEmployee> {

    List<BranchEmployee> findByBranchId(int branchId);

    // Add other specific methods related to the 'branch_employees' table
}