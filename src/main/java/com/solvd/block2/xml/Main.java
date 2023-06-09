package com.solvd.block2.xml;

import static com.solvd.block2.xml.BankXMLParser.parseXML;
import static com.solvd.block2.xml.BankXMLParser.validateXML;

public class Main {
    public static void main(String[] args) {

        String xmlFilePath = "src/main/resources/bankDB.xml";
        String xsdFilePath = "src/main/resources/bankDB.xsd";

        try {
            // Step 1: Validate XML against XSD
            validateXML(xmlFilePath, xsdFilePath);

            // Step 2: Parse XML using SAX parserB
            parseXML(xmlFilePath);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
