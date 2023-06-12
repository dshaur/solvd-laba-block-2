package com.solvd.block2.sql.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "branch")
@XmlAccessorType(XmlAccessType.FIELD)
public class Branch {
    @XmlElement(name = "branchId")
    private int branchId;

    @XmlElement(name = "branchName")
    private String branchName;

    @XmlElement(name = "location")
    private String location;

    @XmlElementWrapper(name = "branchEmployees")
    @XmlElement(name = "branchEmployee")
    private List<BranchEmployee> branchEmployees;

    public Branch(int branchId, String branchName, String location, List<BranchEmployee> branchEmployees) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.location = location;
        this.branchEmployees = branchEmployees;
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

    public List<BranchEmployee> getBranchEmployees() {
        return branchEmployees;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBranchEmployees(List<BranchEmployee> branchEmployees) {
        this.branchEmployees = branchEmployees;
    }

    @Override
    public String toString() {
        return "Branch ID: " + branchId + ", Branch Name: " + branchName + ", Location: " + location +
                ", Branch Employees: " + branchEmployees;
    }
}
