package com.solvd.block2.sql.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.block2.xml.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "creditCard")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCard {
    @JsonProperty("creditCardId")
    @XmlElement(name = "creditCardId")
    private int creditCardId;

    @JsonProperty("customerId")
    @XmlElement(name = "customerId")
    private int customerId;

    @JsonProperty("creditCardNumber")
    @XmlElement(name = "creditCardNumber")
    private String creditCardNumber;

    @JsonProperty("expiryDate")
    @XmlElement(name = "expiryDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expiryDate;

    @JsonProperty("creditLimit")
    @XmlElement(name = "creditLimit")
    private double creditLimit;

    @JsonProperty("outstandingBalance")
    @XmlElement(name = "outstandingBalance")
    private double outstandingBalance;

    public CreditCard() {
        // No-arg default constructor
    }

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

