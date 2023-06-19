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

@XmlRootElement(name = "debitCard")
@XmlAccessorType(XmlAccessType.FIELD)
public class DebitCard {
    @JsonProperty("debitCardId")
    @XmlElement(name = "debitCardId")
    private int debitCardId;

    @JsonProperty("customerId")
    @XmlElement(name = "customerId")
    private int customerId;

    @JsonProperty("cardNumber")
    @XmlElement(name = "cardNumber")
    private String cardNumber;

    @JsonProperty("expirationDate")
    @XmlElement(name = "expirationDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expirationDate;

    public DebitCard() {
        // No-arg default constructor
    }

    public DebitCard(int debitCardId, int customerId, String cardNumber, Date expirationDate) {
        this.debitCardId = debitCardId;
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public int getCardId() {
        return debitCardId;
    }

    public void setCardId(int debitCardId) {
        this.debitCardId = debitCardId;
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
        return "DebitCard { " +
                "debitCardId : " + debitCardId +
                ", customerId : " + customerId +
                ", cardNumber : '" + cardNumber + '\'' +
                ", expirationDate : " + expirationDate +
                '}';
    }
}


