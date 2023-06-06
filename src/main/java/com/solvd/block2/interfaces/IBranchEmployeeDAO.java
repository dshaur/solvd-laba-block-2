package com.solvd.block2.interfaces;

import com.solvd.block2.models.BranchEmployee;

import java.util.List;

public interface IBranchEmployeeDAO extends GenDAO<BranchEmployee> {

    List<BranchEmployee> findByBranchId(int branchId);

    // Add other specific methods related to the 'branch_employees' table
}