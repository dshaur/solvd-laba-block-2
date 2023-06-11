package com.solvd.block2.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
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

            LOGGER.info("XML validation successful.");

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
}







