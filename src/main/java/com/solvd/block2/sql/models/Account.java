package com.solvd.block2.sql.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

    @JsonProperty("accountId")
    @XmlElement(name = "accountId")
    private int accountId;

    @JsonProperty("accountType")
    @XmlElement(name = "accountType")
    private String accountType;

    @JsonProperty("balance")
    @XmlElement(name = "balance")
    private double balance;

    @JsonProperty("openDate")
    @XmlElement(name = "openDate")
    private Date openDate;

    @JsonProperty("lastTransactionDate")
    @XmlElement(name = "lastTransactionDate")
    private Date lastTransactionDate;

    @JsonProperty("branchId")
    @XmlElement(name = "branchId")
    private int branchId;

    @JsonProperty("customers")
    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    private List<Customer> customers;


    public Account() {
        // No-arg default constructor
    }

    public Account(int accountId, String accountType, double balance, Date openDate, Date lastTransactionDate, int branchId, List<Customer> customers) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.openDate = openDate;
        this.lastTransactionDate = lastTransactionDate;
        this.branchId = branchId;
        this.customers = customers;

    }

    // Getters and setters

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getLastTransactionDate() {
        return lastTransactionDate;
    }

    public void setLastTransactionDate(Date lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    @Override
    public String toString() {
        return "Account { " +
                "accountId: " + accountId +
                ", accountType: '" + accountType + '\'' +
                ", balance: " + balance +
                ", openDate: '" + openDate + '\'' +
                ", lastTransactionDate: '" + lastTransactionDate + '\'' +
                ", branchId: " + branchId +
                ", customers: " + customers +
                '}';
    }

}

