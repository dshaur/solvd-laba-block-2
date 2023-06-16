package com.solvd.block2.xml;

import com.solvd.block2.sql.models.CreditCard;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.models.DebitCard;
import com.solvd.block2.sql.models.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JAXBParser {

    private static final Logger LOGGER = LogManager.getLogger(SAXParser.class);

    public static Customer parseBankDB(String xmlFilePath) throws JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Customer customer = (Customer) unmarshaller.unmarshal(new File(xmlFilePath));
            LOGGER.info("JAXB parsing successful.");
            logJAXBParsedFile(customer); // Log the customer object

            return customer;
        } catch (JAXBException e) {
            LOGGER.error("XML parsing failed: " + e.getMessage());
            throw e;
        }
    }

    private static void logJAXBParsedFile(Customer customer) {
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
