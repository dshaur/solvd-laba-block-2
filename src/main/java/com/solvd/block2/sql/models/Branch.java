package com.solvd.block2.sql.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class Branch {
    private int branchId;
    private String branchName;
    private String location;

    @JsonProperty("branchEmployees")
    @XmlElementWrapper(name = "branchEmployees")
    @XmlElement(name = "branchEmployee")
    @JsonManagedReference
    private List<BranchEmployee> branchEmployees;

    @JsonProperty("accounts")
    @XmlElementWrapper(name = "accounts")
    @XmlElement(name = "account")
    private List<Account> accounts;

    public Branch() {
        // No argument constructor
    }

    public Branch(int branchId, String branchName, String location, List<BranchEmployee> branchEmployees, List<Account> accounts) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.location = location;
        this.branchEmployees = branchEmployees;
        this.accounts = accounts;
    }

    // Getters and Setters
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<BranchEmployee> getBranchEmployees() {
        return branchEmployees;
    }

    public void setBranchEmployees(List<BranchEmployee> branchEmployees) {
        this.branchEmployees = branchEmployees;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Branch ID: " + branchId + ", Branch Name: " + branchName + ", Location: " + location +
                ", Branch Employees: " + branchEmployees + ", Accounts: " + accounts;
    }
}