package com.solvd.block2.sql.models;

public class BranchEmployee {

    private int employeeId;
    private int branchId;
    private String firstName;
    private String lastName;
    private String position;

    public BranchEmployee() {
        // No argument constructor
    }

    public BranchEmployee(int employeeId, String firstName, String lastName, int branchId, String position) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.branchId = branchId;
        this.position = position;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Branch ID: " + branchId + ", First Name: " + firstName + ", Last Name: " + lastName
                + ", Position: " + position;
    }
}
