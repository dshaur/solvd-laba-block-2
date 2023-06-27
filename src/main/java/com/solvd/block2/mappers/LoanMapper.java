package com.solvd.block2.mappers;

import com.solvd.block2.sql.models.Loan;

import java.util.List;

public interface LoanMapper {
    Loan getLoanById(int loanId);

    List<Loan> getAllLoans();

    void createLoan(Loan loan);

    void updateLoan(Loan loan);

    void deleteLoan(Loan loan);

    List<Loan> getLoansByCustomerId(int customerId);
}
