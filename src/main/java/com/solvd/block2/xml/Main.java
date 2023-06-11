package com.solvd.block2.xml;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String xmlFilePath = "src/main/resources/xml/bankDB.xml";
        String xsdFilePath = "src/main/resources/xml/bankDB.xsd";

        try {
            // Step 1: Validate XML against XSD
            List<String> validatedElements = BankXMLParser.validateXML(xmlFilePath, xsdFilePath);

            // Step 2: Log the validated elements
            BankXMLParser.logValidatedElements(validatedElements);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


