package com.solvd.block2.services;

import com.solvd.block2.daos.BranchDAO;
import com.solvd.block2.models.Branch;

import java.util.List;

public class BranchService {
    private BranchDAO branchDAO;

    public BranchService(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
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


    // Add other methods as needed
}


