package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.Customer;

import java.util.List;

public interface CustomerMapper {
    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    List<Customer> getCustomersByAccountId(int accountId);
}

