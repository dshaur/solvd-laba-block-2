package com.solvd.block2.business_logic;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class RandomHelper {
    private static final String[] ACCOUNT_TYPES = {"Checking", "Savings"};
    private static final int[] BRANCH_IDS = {1, 2, 3};

    private static final String[] LOAN_TYPES = {"Car Loan", "Home Loan"};


    public static String getRandomAccountType() {
        int index = new Random().nextInt(ACCOUNT_TYPES.length);
        return ACCOUNT_TYPES[index];
    }

    public static int getRandomBranchId() {
        int index = new Random().nextInt(BRANCH_IDS.length);
        return BRANCH_IDS[index];
    }

    public static String generateRandomCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);

            if ((i + 1) % 4 == 0 && i < 15) {
                cardNumber.append(" ");
            }
        }

        return cardNumber.toString();
    }

    public static Date generateRandomExpiryDate() {
        int year = LocalDate.now().getYear() + new Random().nextInt(4); // Expiry date within the next 4 years
        int month = new Random().nextInt(12) + 1; // Month ranges from 1 to 12
        int day = new Random().nextInt(28) + 1; // Day ranges from 1 to 28

        LocalDate expiryLocalDate = LocalDate.of(year, month, day);
        return java.sql.Date.valueOf(expiryLocalDate);
    }


    public static double getRandomCreditLimit() {
        double minCreditLimit = 1000.0;
        double maxCreditLimit = 16000.0;

        return minCreditLimit + (maxCreditLimit - minCreditLimit) * new Random().nextDouble();
    }

    public static double getRandomLoanAmount() {
        double minLoanAmount = 1000.0;
        double maxLoanAmount = 250000.0;

        return minLoanAmount + (maxLoanAmount - minLoanAmount) * new Random().nextDouble();
    }

    public static double getRandomInterestRate() {
        double minInterestRate = 2.0;
        double maxInterestRate = 10.0;

        return minInterestRate + (maxInterestRate - minInterestRate) * new Random().nextDouble();
    }

    public static String getRandomLoanType() {
        int randomIndex = new Random().nextInt(LOAN_TYPES.length);
        return LOAN_TYPES[randomIndex];
    }

    public static double getRandomAccountBalance() {

        double minBalance = 100.0; // Minimum first deposit balance
        double maxBalance = 10000.0; // Maximum first deposit balance

        // Generate a random double value within the specified range
        double randomBalance = minBalance + (maxBalance - minBalance) * new Random().nextDouble();

        // Round the balance to two decimal places
        randomBalance = Math.round(randomBalance * 100.0) / 100.0;

        return randomBalance;

    }
}

