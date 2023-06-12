package com.solvd.block2.sql.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transactionType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionType {
    @XmlElement(name = "typeId")
    private int typeId;

    @XmlElement(name = "typeName")
    private String typeName;
    
    public TransactionType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Type ID: " + typeId + ", Type Name: " + typeName;
    }
}

