package com.solvd.block2.sql.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @XmlElement(name = "customerId")
    private int customerId;

    @XmlElement(name = "firstName")
    private String firstName;

    @XmlElement(name = "lastName")
    private String lastName;

    @XmlElement(name = "address")
    private String address;

    @XmlElement(name = "phoneNumber")
    private String phoneNumber;

    @XmlElement(name = "email")
    private String email;

    @XmlElementWrapper(name = "creditCards")
    @XmlElement(name = "creditCard")
    private List<CreditCard> creditCards;

    @XmlElementWrapper(name = "debitCards")
    @XmlElement(name = "debitCard")
    private List<DebitCard> debitCards;

    @XmlElementWrapper(name = "loans")
    @XmlElement(name = "loan")
    private List<Loan> loans;

    public Customer() {
        // No-arg default constructor
    }

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

