package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.Branch;

import java.sql.SQLException;
import java.util.List;

public interface IBranchDAO extends GenDAO<Branch> {

    List<Branch> findByLocation(String location) throws SQLException;

    List<Branch> findByBranchName(String branchName) throws SQLException;

    // Add other specific methods related to the 'branches' table

}

