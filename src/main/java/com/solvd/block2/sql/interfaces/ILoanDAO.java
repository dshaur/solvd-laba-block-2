package com.solvd.block2.sql.interfaces;

import com.solvd.block2.sql.models.Loan;

import java.util.List;

public interface ILoanDAO extends GenDAO<Loan> {
    Loan getById(int loanId);

    List<Loan> getByCustomerId(int customerId);

    List<Loan> getAll();

    void create(Loan loan);

    void update(Loan loan);

    void delete(Loan loan);
}


