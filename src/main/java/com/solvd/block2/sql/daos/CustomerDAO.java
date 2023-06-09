package com.solvd.block2.sql.daos;

import com.solvd.block2.sql.interfaces.ICustomerDAO;
import com.solvd.block2.sql.models.CreditCard;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.models.DebitCard;
import com.solvd.block2.sql.models.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.block2.sql.utilities.DbUtil.getConnection;

public class CustomerDAO extends AbstractDAO<Customer> implements ICustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);

    private CreditCardDAO creditCardDAO;
    private DebitCardDAO debitCardDAO;
    private LoanDAO loanDAO;

    public CustomerDAO() {
        creditCardDAO = new CreditCardDAO();
        debitCardDAO = new DebitCardDAO();
        loanDAO = new LoanDAO();
    }

    @Override
    protected Customer createFromResultSet(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("Customer_ID");
        String firstName = resultSet.getString("First_Name");
        String lastName = resultSet.getString("Last_Name");
        String address = resultSet.getString("Address");
        String phoneNumber = resultSet.getString("Phone_Number");
        String email = resultSet.getString("Email");

        List<CreditCard> creditCards = getCreditCardsByCustomerId(customerId);
        List<DebitCard> debitCards = getDebitCardsByCustomerId(customerId);
        List<Loan> loans = getLoansByCustomerId(customerId);

        return new Customer(customerId, firstName, lastName, address, phoneNumber, email, creditCards, debitCards, loans);
    }

    @Override
    protected void setCreateStatementParameters(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setInt(1, customer.getCustomerId());
        statement.setString(2, customer.getFirstName());
        statement.setString(3, customer.getLastName());
        statement.setString(4, customer.getAddress());
        statement.setString(5, customer.getPhoneNumber());
        statement.setString(6, customer.getEmail());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getFirstName());
        statement.setString(2, customer.getLastName());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getPhoneNumber());
        statement.setString(5, customer.getEmail());
        statement.setInt(6, customer.getCustomerId());
    }

    @Override
    protected void setDeleteStatementParameters(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setInt(1, customer.getCustomerId());
    }

    @Override
    protected String getFindByIdQuery() {
        return "SELECT * FROM customers WHERE Customer_ID = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM customers";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO customers (Customer_ID, First_Name, Last_Name, Address, Phone_Number, Email) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE customers SET First_Name = ?, Last_Name = ?, Address = ?, Phone_Number = ?, Email = ? WHERE Customer_ID = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM customers WHERE Customer_ID = ?";
    }

    private List<CreditCard> getCreditCardsByCustomerId(int customerId) {
        return creditCardDAO.getByCustomerId(customerId);
    }

    private List<DebitCard> getDebitCardsByCustomerId(int customerId) {
        return debitCardDAO.getByCustomerId(customerId);
    }

    private List<Loan> getLoansByCustomerId(int customerId) {
        return loanDAO.getByCustomerId(customerId);
    }

    @Override
    public Customer getById(int customerId) {
        LOGGER.info("Getting customer with ID: {}", customerId);
        try (PreparedStatement statement = getConnection().prepareStatement(getFindByIdQuery())) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting customer with ID: {}", customerId, e);
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        LOGGER.info("Getting all customers");
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(getFindAllQuery())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting all customers", e);
        }
        return customers;
    }
}


