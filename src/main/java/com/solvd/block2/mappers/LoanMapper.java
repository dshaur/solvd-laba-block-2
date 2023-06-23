package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.Loan;

import java.util.List;

public interface LoanMapper {
    Loan getLoanById(int loanId);

    List<Loan> getAllLoans();

    void insertLoan(Loan loan);

    void updateLoan(Loan loan);

    void deleteLoan(int loanId);

    List<Loan> getLoansByCustomerId(int customerId);
}
