package com.solvd.block2.observers;

import com.solvd.block2.sql.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerLogger implements CustomerObserver {

    private static final Logger LOGGER = LogManager.getLogger(CustomerLogger.class);

    @Override
    public void onCustomerCreated(Customer customer) {
        LOGGER.info("New customer created: " + "Customer ID: " + customer.getCustomerId() + ", First Name: " +
                customer.getFirstName() + ", Last Name: " + customer.getLastName());
    }
}
