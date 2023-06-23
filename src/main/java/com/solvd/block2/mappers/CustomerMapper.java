package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.Customer;

import java.util.List;

public interface CustomerMapper {
    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    void insertCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId);
}

