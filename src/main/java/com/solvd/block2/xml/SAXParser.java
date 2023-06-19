package com.solvd.block2.xml;

import com.solvd.block2.sql.models.CreditCard;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.models.DebitCard;
import com.solvd.block2.sql.models.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SAXParser {

    private static final Logger LOGGER = LogManager.getLogger(SAXParser.class);

    public static Customer parseXML(String xmlFilePath, String xsdFilePath) throws SAXException {
        try {
            BankContentHandler contentHandler = new BankContentHandler();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));

            factory.setSchema(schema);

            javax.xml.parsers.SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            List<String> validationErrors = new ArrayList<>();
            reader.setErrorHandler(new SimpleErrorHandler(validationErrors));

            reader.setContentHandler(contentHandler);

            reader.parse(xmlFilePath);

            if (!validationErrors.isEmpty()) {
                throw new SAXException("XML validation failed.");
            }

            Customer customer = contentHandler.getCustomer();
            customer.setCreditCards(contentHandler.getCreditCards());
            customer.setDebitCards(contentHandler.getDebitCards());
            customer.setLoans(contentHandler.getLoans());

            LOGGER.info("SAX parsing successful.");
            logSAXParsedFile(customer); // Log the customer object

            return customer;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new SAXException("Parser configuration error: " + e.getMessage());
        } catch (SAXException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SAXException("XML parsing failed. Error: " + e.getMessage());
        }
    }

    private static void logSAXParsedFile(Customer customer) {
        // Access the parsed customer object
        LOGGER.info(customer);

        // Access parsed credit cards
        List<CreditCard> creditCards = customer.getCreditCards();
        LOGGER.info(creditCards);

        // Access parsed debit cards
        List<DebitCard> debitCards = customer.getDebitCards();
        LOGGER.info(debitCards);

        // Access parsed loans
        List<Loan> loans = customer.getLoans();
        LOGGER.info(loans);
    }
}










