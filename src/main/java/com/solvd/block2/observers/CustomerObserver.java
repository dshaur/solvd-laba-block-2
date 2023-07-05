package com.solvd.block2.observers;

import com.solvd.block2.sql.models.Customer;

public interface CustomerObserver {
    void onCustomerCreated(Customer customer);

}
