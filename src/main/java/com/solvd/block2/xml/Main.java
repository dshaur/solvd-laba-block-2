package com.solvd.block2.xml;

import com.solvd.block2.sql.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        String xmlFilePath = "src/main/resources/xml/bankDB.xml";
        String xsdFilePath = "src/main/resources/xml/bankDB.xsd";

        try {
            // Parse and validate the XML using SAX
            Customer saxCustomer = SAXParser.parseXML(xmlFilePath, xsdFilePath);

            // Parse the XML using JAXB
            Customer jaxCustomer = JAXBParser.parseBankDB(xmlFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


