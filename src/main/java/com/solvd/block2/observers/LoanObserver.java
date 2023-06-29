package com.solvd.block2.observers;

import com.solvd.block2.sql.models.Loan;

public interface LoanObserver {

    void onLoanCreated(Loan loan);

}
