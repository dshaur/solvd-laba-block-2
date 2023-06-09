package com.solvd.block2.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BankContentHandler extends DefaultHandler {
    private StringBuilder dataBuffer;
    private boolean isParsingValue;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("customer") ||
                qName.equalsIgnoreCase("creditCard") ||
                qName.equalsIgnoreCase("debitCard") ||
                qName.equalsIgnoreCase("loan") ||
                qName.equalsIgnoreCase("transactionType") ||
                qName.equalsIgnoreCase("branchEmployee")) {
            System.out.println(qName + ":");
        }
        dataBuffer = new StringBuilder();
        isParsingValue = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isParsingValue) {
            String value = dataBuffer.toString().trim();
            if (!value.isEmpty()) {
                System.out.println(qName + ": " + value);
            }
            isParsingValue = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isParsingValue) {
            dataBuffer.append(ch, start, length);
        }
    }
}

