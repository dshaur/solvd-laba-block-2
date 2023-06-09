package com.solvd.block2.sql.models;

import java.util.Date;

public class DebitCard {
    private int cardId;
    private int customerId;
    private String cardNumber;
    private Date expirationDate;

    public DebitCard(int cardId, int customerId, String cardNumber, Date expirationDate) {
        this.cardId = cardId;
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "cardId=" + cardId +
                ", customerId=" + customerId +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}


