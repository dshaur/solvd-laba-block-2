package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.Branch;

import java.util.List;

public interface BranchMapper {
    Branch getBranchById(int id);

    List<Branch> getAllBranches();

    void createBranch(Branch branch);

    void updateBranch(Branch branch);

    void deleteBranch(Branch branch);

    List<Branch> getBranchesByLocation(String location);
}
