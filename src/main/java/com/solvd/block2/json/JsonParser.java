package com.solvd.block2.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.block2.sql.models.CreditCard;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.models.DebitCard;
import com.solvd.block2.sql.models.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser {
    private static final Logger LOGGER = LogManager.getLogger(JsonParser.class);

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/main/resources/json/bankDB.json");

        try {
            Customer customer = objectMapper.readValue(jsonFile, Customer.class);
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
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
