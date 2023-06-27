package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.Loan;

import java.sql.SQLException;
import java.util.List;

public interface ILoanDAO extends GenDAO<Loan> {
    Loan getById(int loanId) throws SQLException;

    List<Loan> getByCustomerId(int customerId) throws SQLException;

    List<Loan> getAll() throws SQLException;

    void create(Loan loan) throws SQLException;

    void update(Loan loan) throws SQLException;

    void delete(Loan loan) throws SQLException;
}


