package com.solvd.block2.observers;

import com.solvd.block2.sql.models.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoanLogger implements LoanObserver {

    private static final Logger LOGGER = LogManager.getLogger(LoanLogger.class);

    @Override
    public void onLoanCreated(Loan loan) {
        LOGGER.info("New loan created: " + "CustomerId: " + loan.getCustomerId() + ", LoanType: " + loan.getLoanType() + ", LoanAmount: " + loan.getLoanAmount()
                + ", InterestRate: " + loan.getInterestRate() + ", StartDate: " + loan.getStartDate() + ", EndDate: " + loan.getEndDate());
    }

}
