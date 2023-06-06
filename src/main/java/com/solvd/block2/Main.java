package com.solvd.block2;

import com.solvd.block2.daos.BranchDAO;
import com.solvd.block2.daos.BranchEmployeeDAO;
import com.solvd.block2.models.Branch;
import com.solvd.block2.models.BranchEmployee;
import com.solvd.block2.services.BranchEmployeeService;
import com.solvd.block2.services.BranchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // Create instances of DAO and Service
        BranchDAO branchDAO = new BranchDAO();
        BranchService branchService = new BranchService(branchDAO);

        BranchEmployeeDAO branchEmployeeDAO = new BranchEmployeeDAO();
        BranchEmployeeService branchEmployeeService = new BranchEmployeeService(branchEmployeeDAO);

        /* ********************* Test display operations ********************* */

        // Get branches with ID 1
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

        // Get all branch employees for a specific branch ID
        int EmployeeBranchId = 1; // Change the branch ID as per schema data
        try {
            List<BranchEmployee> branchEmployeesByBranchId = branchEmployeeService.getBranchEmployeesByBranchId(EmployeeBranchId);
            if (!branchEmployeesByBranchId.isEmpty()) {
                LOGGER.info("Branch Employees found for branch ID " + EmployeeBranchId + ": " + branchEmployeesByBranchId);
            } else {
                LOGGER.info("No Branch Employees found for branch ID: " + EmployeeBranchId);
            }
        } catch (Exception e) {
            LOGGER.info("Error retrieving Branch Employees by branch ID: " + EmployeeBranchId + ". " + e.getMessage());
        }

    }

}


