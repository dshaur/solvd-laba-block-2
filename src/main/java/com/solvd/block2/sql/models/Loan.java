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

@XmlRootElement(name = "loan")
@XmlAccessorType(XmlAccessType.FIELD)
public class Loan {
    @JsonProperty("loanId")
    @XmlElement(name = "loanId")
    private int loanId;

    @JsonProperty("customerId")
    @XmlElement(name = "customerId")
    private int customerId;

    @JsonProperty("loanType")
    @XmlElement(name = "loanType")
    private String loanType;

    @JsonProperty("loanAmount")
    @XmlElement(name = "loanAmount")
    private double loanAmount;

    @JsonProperty("interestRate")
    @XmlElement(name = "interestRate")
    private double interestRate;

    @JsonProperty("startDate")
    @XmlElement(name = "startDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonProperty("endDate")
    @XmlElement(name = "endDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    public Loan() {
        // No-arg default constructor
    }

    public Loan(int loanId, int customerId, String loanType, double loanAmount, double interestRate, Date startDate, Date endDate) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Loan { " +
                "loanId : " + loanId +
                ", customerId : " + customerId +
                ", loanType : '" + loanType + '\'' +
                ", loanAmount : " + loanAmount +
                ", interestRate : " + interestRate +
                ", startDate : " + startDate +
                ", endDate : " + endDate +
                '}';
    }
}


