package com.solvd.block2.sql.models;

import com.solvd.block2.xml.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "debitCard")
@XmlAccessorType(XmlAccessType.FIELD)
public class DebitCard {
    @XmlElement(name = "cardId")
    private int cardId;
    @XmlElement(name = "customerId")
    private int customerId;
    @XmlElement(name = "cardNumber")
    private String cardNumber;
    @XmlElement(name = "expirationDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date expirationDate;

    public DebitCard() {
        // No-arg default constructor
    }

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


