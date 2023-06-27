package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO extends GenDAO<Customer> {
    Customer getById(int customerId) throws SQLException;

    List<Customer> getAll() throws SQLException;

    void create(Customer customer) throws SQLException;

    void update(Customer customer) throws SQLException;

    void delete(Customer customer) throws SQLException;
}

