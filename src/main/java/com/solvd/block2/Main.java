package com.solvd.block2;

import com.solvd.block2.daos.BranchDAO;
import com.solvd.block2.models.Branch;
import com.solvd.block2.services.BranchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            // Create instances of DAO and Service
            BranchDAO branchDAO = new BranchDAO();
            BranchService branchService = new BranchService(branchDAO);

            // Test operations

            //  Get all branches with ID 1
            int branchId = 1; // Change the branch ID as per schema data
            try {
                Branch branch = branchService.getBranchById(branchId);
                if (branch != null) {
                    LOGGER.info("Branch found with ID: " + branchId);
                } else {
                    LOGGER.info("Branch not found with ID: " + branchId);
                }
            } catch (Exception e) {
                LOGGER.info("Error retrieving branch with ID: " + branchId + ". " + e.getMessage());
            }

            // Get all branches by location
            String location = "New York"; // Change the location as per schema data
            try {
                List<Branch> branchesByLocation = branchService.getBranchesByLocation(location);
                if (!branchesByLocation.isEmpty()) {
                    LOGGER.info("Branches found at location '" + location + "': " + branchesByLocation);
                } else {
                    LOGGER.info("No branches found at location: " + location);
                }
            } catch (Exception e) {
                LOGGER.info("Error retrieving branches by location: " + location + ". " + e.getMessage());
            }

            // Get all branches by name
            String branchName = "Main Branch"; // Change the branch name as per schema data
            try {
                List<Branch> branchesByBranchName = branchService.getBranchesByBranchName(branchName);
                if (!branchesByBranchName.isEmpty()) {
                    LOGGER.info("Branches found with name '" + branchName + "': " + branchesByBranchName);
                } else {
                    LOGGER.info("No branches found with name: " + branchName);
                }
            } catch (Exception e) {
                LOGGER.info("Error retrieving branches by name: " + branchName + ". " + e.getMessage());
            }
        } catch (Exception e) {
            LOGGER.info("An error occurred: " + e.getMessage());
        }
    }

}


