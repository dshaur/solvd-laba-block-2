package com.solvd.block2.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import java.util.List;

public class SimpleErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LogManager.getLogger(SimpleErrorHandler.class);
    private List<String> validationErrors;

    public SimpleErrorHandler(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public void warning(SAXParseException e) throws SAXParseException {
        validationErrors.add("Warning: " + e.getMessage());
        LOGGER.warn("Warning: " + e.getMessage());
    }

    public void error(SAXParseException e) throws SAXParseException {
        validationErrors.add("Error: " + e.getMessage());
        LOGGER.error("Error: " + e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXParseException {
        validationErrors.add("Fatal Error: " + e.getMessage());
        LOGGER.fatal("Fatal Error: " + e.getMessage());
    }
}





