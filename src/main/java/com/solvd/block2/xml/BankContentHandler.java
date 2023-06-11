package com.solvd.block2.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

class BankContentHandler extends DefaultHandler {
    private StringBuilder dataBuffer;
    private boolean isParsingValue;
    private List<String> validatedElements;

    public BankContentHandler(List<String> validatedElements) {
        this.validatedElements = validatedElements;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        dataBuffer = new StringBuilder();
        isParsingValue = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isParsingValue) {
            String value = dataBuffer.toString().trim();
            if (!value.isEmpty()) {
                validatedElements.add(qName + ": " + value);
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



