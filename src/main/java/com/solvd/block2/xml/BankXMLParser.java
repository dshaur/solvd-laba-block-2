package com.solvd.block2.xml;

import com.solvd.block2.sql.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BankXMLParser {

    private static final Logger LOGGER = LogManager.getLogger(BankXMLParser.class);

    static List<String> validateXML(String xmlFilePath, String xsdFilePath) throws SAXException {
        List<String> validatedElements = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));

            factory.setSchema(schema);

            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            List<String> validationErrors = new ArrayList<>();
            reader.setErrorHandler(new SimpleErrorHandler(validationErrors));

            BankContentHandler contentHandler = new BankContentHandler(validatedElements);
            reader.setContentHandler(contentHandler);

            reader.parse(xmlFilePath);

            if (!validationErrors.isEmpty()) {
                throw new SAXException("XML validation failed.");
            }

            LOGGER.info("JAX validation and parsing successful.");

            return validatedElements;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new SAXException("Parser configuration error: " + e.getMessage());
        } catch (SAXException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SAXException("XML validation failed. Error: " + e.getMessage());
        }
    }

    static void logValidatedElements(List<String> validatedElements) {
        for (String element : validatedElements) {
            LOGGER.info(element);
        }
    }

    public static Customer parseBankDB(String xmlFilePath) throws JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Customer customer = (Customer) unmarshaller.unmarshal(new File(xmlFilePath));
            LOGGER.info("JAXB parsing successful.");
            logParsedCustomer(customer); // Log the customer object

            return customer;
        } catch (JAXBException e) {
            LOGGER.error("XML parsing failed: " + e.getMessage());
            throw e;
        }
    }

    private static void logParsedCustomer(Customer customer) {
        LOGGER.info("Unmarshalled XML:");
        LOGGER.info("  Customer ID: " + customer.getCustomerId());
        LOGGER.info("  First Name: " + customer.getFirstName());
        LOGGER.info("  Last Name: " + customer.getLastName());
        LOGGER.info("  Address: " + customer.getAddress());
        LOGGER.info("  Phone Number: " + customer.getPhoneNumber());
        LOGGER.info("  Email: " + customer.getEmail());
        LOGGER.info("  Credit Cards: " + customer.getCreditCards());
        LOGGER.info("  Debit Cards: " + customer.getDebitCards());
        LOGGER.info("  Loans: " + customer.getLoans());
    }


}







