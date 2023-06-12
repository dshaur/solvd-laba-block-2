package com.solvd.block2.sql.models;

import com.solvd.block2.xml.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
    @XmlElement(name = "transactionId")
    private int transactionId;

    @XmlElement(name = "transactionTypes")
    private TransactionType transactionTypes;

    @XmlElement(name = "accountId")
    private int accountId;

    @XmlElement(name = "amount")
    private double amount;

    @XmlElement(name = "transactionDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date transactionDate;

    @XmlElement(name = "sourceAccountId")
    private int sourceAccountId;

    @XmlElement(name = "destinationAccountId")
    private int destinationAccountId;

    public Transaction(int transactionId, TransactionType transactionTypes, int accountId, double amount, Date transactionDate, int sourceAccountId, int destinationAccountId) {
        this.transactionId = transactionId;
        this.transactionTypes = transactionTypes;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    // Getters and setters

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionTypes() {
        return transactionTypes;
    }

    public void setTransactionTypes(TransactionType transactionTypes) {
        this.transactionTypes = transactionTypes;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(int sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public int getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(int destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                ", Transaction Type: " + transactionTypes +
                ", Account ID: " + accountId +
                ", Amount: " + amount +
                ", Transaction Date: " + transactionDate +
                ", Source Account ID: " + sourceAccountId +
                ", Destination Account ID: " + destinationAccountId +
                " }";
    }
}

