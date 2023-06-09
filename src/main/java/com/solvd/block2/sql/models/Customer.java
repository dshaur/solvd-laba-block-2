package com.solvd.block2.sql.models;

import java.util.List;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private List<CreditCard> creditCards;
    private List<DebitCard> debitCards;
    private List<Loan> loans;

    public Customer(int customerId, String firstName, String lastName, String address, String phoneNumber, String email, List<CreditCard> creditCards, List<DebitCard> debitCards, List<Loan> loans) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creditCards = creditCards;
        this.debitCards = debitCards;
        this.loans = loans;
    }

    // Getters and setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public List<DebitCard> getDebitCards() {
        return debitCards;
    }

    public void setDebitCards(List<DebitCard> debitCards) {
        this.debitCards = debitCards;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "Customer { " +
                "customerId: " + customerId +
                ", firstName: '" + firstName + '\'' +
                ", lastName: '" + lastName + '\'' +
                ", address: '" + address + '\'' +
                ", phoneNumber: '" + phoneNumber + '\'' +
                ", email: '" + email + '\'' +
                ", creditCards: " + creditCards +
                ", debitCards: " + debitCards +
                ", loans: " + loans +
                '}';
    }
}

