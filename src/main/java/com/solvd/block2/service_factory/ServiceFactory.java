package com.solvd.block2.service_factory;

import com.solvd.block2.sql.daos.*;
import com.solvd.block2.sql.services.*;

public class ServiceFactory {

    private static final String daoService = "dao";
    private static final String mybatisService = "mybatis";

    public static IAccountService createAccountService(String type) {
        switch (type.toLowerCase()) {
            case daoService:
                return new AccountService(new AccountDAO());
            case mybatisService:
                return new MyBatisAccountService();
            default:
                return null;
        }
    }


    public static IBranchService createBranchService(String type) {
        switch (type.toLowerCase()) {
            case daoService:
                return new BranchService(new BranchDAO(), new BranchEmployeeDAO());
            case mybatisService:
                return new MyBatisBranchService();
            default:
                return null;
        }

    }

    public static ICustomerService createCustomerService(String type) {
        switch (type.toLowerCase()) {
            case daoService:
                return new CustomerService(new CustomerDAO(), new DebitCardDAO(), new CreditCardDAO(),
                        new LoanDAO(), new AccountDAO());
            case mybatisService:
                return new MyBatisCustomerService();
            default:
                return null;
        }
    }

    public static ITransactionService createTransactionService(String type) {
        switch (type.toLowerCase()) {
            case daoService:
                return new TransactionService(new TransactionDAO(), new TransactionTypeDAO());
            case mybatisService:
                return new MyBatisTransactionService();
            default:
                return null;
        }
    }
}


