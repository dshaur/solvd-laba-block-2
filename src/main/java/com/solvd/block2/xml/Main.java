package com.solvd.block2.xml;

import com.solvd.block2.sql.models.Customer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String xmlFilePath = "src/main/resources/xml/bankDB.xml";
        String xsdFilePath = "src/main/resources/xml/bankDB.xsd";

        try {
            // Step 1: Validate XML against XSD and Parse the XML using SAX
            List<String> validatedElements = BankXMLParser.validateXML(xmlFilePath, xsdFilePath);

            // Step 2: Log the parsed and validated elements
            BankXMLParser.logValidatedElements(validatedElements);

            // Step 3: Parse the XML using JAXB
            Customer customer = BankXMLParser.parseBankDB(xmlFilePath);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


