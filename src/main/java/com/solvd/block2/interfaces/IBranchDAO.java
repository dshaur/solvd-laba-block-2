package com.solvd.block2.interfaces;

import com.solvd.block2.models.Branch;

import java.util.List;

public interface IBranchDAO extends GenDAO<Branch> {

    List<Branch> findByLocation(String location);

    List<Branch> findByBranchName(String branchName);

    // Add other specific methods related to the 'branches' table

}

