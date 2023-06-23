package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.BranchEmployee;

import java.util.List;

public interface BranchEmployeeMapper {
    BranchEmployee getBranchEmployeeById(int id);

    List<BranchEmployee> getAllBranchEmployees();

    void insertBranchEmployee(BranchEmployee branchEmployee);

    void updateBranchEmployee(BranchEmployee branchEmployee);

    void deleteBranchEmployee(int id);

    List<BranchEmployee> getBranchEmployeesByBranchId(int branchId);
}
