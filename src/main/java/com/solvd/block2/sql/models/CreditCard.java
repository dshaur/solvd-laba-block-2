package com.solvd.block2.sql.models;

import java.util.Date;

public class CreditCard {
    private int creditCardId;
    private int customerId;
    private String creditCardNumber;
    private Date expiryDate;
    private double creditLimit;
    private double outstandingBalance;

    public CreditCard(int creditCardId, int customerId, String creditCardNumber, Date expiryDate, double creditLimit, double outstandingBalance) {
        this.creditCardId = creditCardId;
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.creditLimit = creditLimit;
        this.outstandingBalance = outstandingBalance;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    @Override
    public String toString() {
        return "CreditCard { " +
                "creditCardId: " + creditCardId +
                ", customerId: " + customerId +
                ", creditCardNumber: '" + creditCardNumber + '\'' +
                ", expiryDate: '" + expiryDate + '\'' +
                ", creditLimit: " + creditLimit +
                ", outstandingBalance: " + outstandingBalance +
                '}';
    }
}

