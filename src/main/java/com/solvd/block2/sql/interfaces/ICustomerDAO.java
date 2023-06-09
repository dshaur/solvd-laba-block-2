package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.Customer;

import java.util.List;

public interface ICustomerDAO extends GenDAO<Customer> {
    Customer getById(int customerId);

    List<Customer> getAll();

    void create(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);
}

