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

public class BankXMLParser {

    private static final Logger LOGGER = LogManager.getLogger(BankXMLParser.class);

    static void validateXML(String xmlFilePath, String xsdFilePath) throws SAXException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));

            factory.setSchema(schema);

            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setErrorHandler(new SimpleErrorHandler());

            reader.parse(xmlFilePath);

            System.out.println("XML validation successful.");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new SAXException("Parser configuration error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SAXException("XML validation failed. Error: " + e.getMessage());
        }
    }

    static void parseXML(String xmlFilePath) throws SAXException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);

            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new BankContentHandler());

            reader.parse(xmlFilePath);

            System.out.println("XML parsing completed.");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new SAXException("Parser configuration error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SAXException("XML parsing failed. Error: " + e.getMessage());
        }
    }
}






