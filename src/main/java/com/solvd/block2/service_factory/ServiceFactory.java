package com.solvd.block2.service_factory;

import com.solvd.block2.sql.daos.*;
import com.solvd.block2.sql.services.*;

public class ServiceFactory {

    private static final String DAO_SERVICE = "dao";
    private static final String MYBATIS_SERVICE = "mybatis";

    public static IAccountService createAccountService() {
        String serviceType = System.getProperty("service.type");
        switch (serviceType.toLowerCase()) {
            case DAO_SERVICE:
                return new AccountService(new AccountDAO());
            case MYBATIS_SERVICE:
                return new MyBatisAccountService();
            default:
                return null;
        }
    }


    public static IBranchService createBranchService() {
        String serviceType = System.getProperty("service.type");
        switch (serviceType.toLowerCase()) {
            case DAO_SERVICE:
                return new BranchService(new BranchDAO(), new BranchEmployeeDAO());
            case MYBATIS_SERVICE:
                return new MyBatisBranchService();
            default:
                return null;
        }

    }

    public static ICustomerService createCustomerService() {
        String serviceType = System.getProperty("service.type");
        switch (serviceType.toLowerCase()) {
            case DAO_SERVICE:
                return new CustomerService(new CustomerDAO(), new DebitCardDAO(), new CreditCardDAO(),
                        new LoanDAO(), new AccountDAO());
            case MYBATIS_SERVICE:
                return new MyBatisCustomerService();
            default:
                return null;
        }
    }

    public static ITransactionService createTransactionService() {
        String serviceType = System.getProperty("service.type");
        switch (serviceType.toLowerCase()) {
            case DAO_SERVICE:
                return new TransactionService(new TransactionDAO(), new TransactionTypeDAO());
            case MYBATIS_SERVICE:
                return new MyBatisTransactionService();
            default:
                return null;
        }
    }
}


