package com.solvd.block2.sql.models;

public class TransactionType {

    private int typeId;
    private String typeName;

    public TransactionType() {
        // No argument constructor
    }

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

