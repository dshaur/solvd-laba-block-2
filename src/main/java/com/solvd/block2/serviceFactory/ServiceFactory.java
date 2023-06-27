package com.solvd.block2.serviceFactory;

import com.solvd.block2.sql.daos.*;
import com.solvd.block2.sql.services.*;

public class ServiceFactory {

    public static IAccountService createAccountService(String type) {
        switch (type.toLowerCase()) {
            case "dao":
                CustomerDAO customerDAO = new CustomerDAO();
                return new AccountService(new AccountDAO());
            case "mybatis":
                return new MyBatisAccountService();
            default:
                return null;
        }
    }


    public static IBranchService createBranchService(String type) {
        switch (type.toLowerCase()) {
            case "dao":
                return new BranchService(new BranchDAO(), new BranchEmployeeDAO());
            case "mybatis":
                return new MyBatisBranchService();
            default:
                return null;
        }

    }

    public static ICustomerService createCustomerService(String type) {
        switch (type.toLowerCase()) {
            case "dao":
                CustomerDAO customerDAO = new CustomerDAO();
                return new CustomerService(new CustomerDAO(), new DebitCardDAO(), new CreditCardDAO(),
                        new LoanDAO(), new AccountDAO());
            case "mybatis":
                return new MyBatisCustomerService();
            default:
                return null;
        }
    }

    public static ITransactionService createTransactionService(String type) {
        switch (type.toLowerCase()) {
            case "dao":
                return new TransactionService(new TransactionDAO(), new TransactionTypeDAO());
            case "mybatis":
                return new MyBatisTransactionService();
            default:
                return null;
        }
    }
}


