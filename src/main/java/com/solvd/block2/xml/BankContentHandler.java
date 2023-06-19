package com.solvd.block2.xml;

import com.solvd.block2.sql.models.CreditCard;
import com.solvd.block2.sql.models.Customer;
import com.solvd.block2.sql.models.DebitCard;
import com.solvd.block2.sql.models.Loan;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class BankContentHandler extends DefaultHandler {
    private StringBuilder dataBuffer;
    private boolean isParsingValue;
    private Customer customer;
    private List<CreditCard> creditCards;
    private List<DebitCard> debitCards;
    private List<Loan> loans;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        dataBuffer = new StringBuilder();
        isParsingValue = true;

        switch (qName) {
            case "customer":
                customer = new Customer();
                break;
            case "creditCard":
                if (creditCards == null) {
                    creditCards = new ArrayList<>();
                }
                CreditCard creditCard = new CreditCard();
                creditCards.add(creditCard);
                break;
            case "debitCard":
                if (debitCards == null) {
                    debitCards = new ArrayList<>();
                }
                DebitCard debitCard = new DebitCard();
                debitCards.add(debitCard);
                break;
            case "loan":
                if (loans == null) {
                    loans = new ArrayList<>();
                }
                Loan loan = new Loan();
                loans.add(loan);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isParsingValue) {
            String value = dataBuffer.toString().trim();

            if (customer != null) {
                if (qName.equals("customerId")) {
                    customer.setCustomerId(Integer.parseInt(value));
                } else if (qName.equals("firstName")) {
                    customer.setFirstName(value);
                } else if (qName.equals("lastName")) {
                    customer.setLastName(value);
                } else if (qName.equals("address")) {
                    customer.setAddress(value);
                } else if (qName.equals("phoneNumber")) {
                    customer.setPhoneNumber(value);
                } else if (qName.equals("email")) {
                    customer.setEmail(value);
                }
            }

            if (creditCards != null) {
                CreditCard creditCard = creditCards.get(creditCards.size() - 1);
                if (qName.equals("creditCardId")) {
                    creditCard.setCreditCardId(Integer.parseInt(value));
                } else if (qName.equals("customerId")) {
                    creditCard.setCustomerId(Integer.parseInt(value));
                } else if (qName.equals("creditCardNumber")) {
                    creditCard.setCreditCardNumber(value);
                } else if (qName.equals("expiryDate")) {
                    // Parse the date using SimpleDateFormat or any other date parsing method
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date expiryDate = dateFormat.parse(value);
                        creditCard.setExpiryDate(expiryDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (qName.equals("creditLimit")) {
                    creditCard.setCreditLimit(Double.parseDouble(value));
                } else if (qName.equals("outstandingBalance")) {
                    creditCard.setOutstandingBalance(Double.parseDouble(value));
                }
            }

            if (debitCards != null) {
                DebitCard debitCard = debitCards.get(debitCards.size() - 1);
                if (qName.equals("cardId")) {
                    debitCard.setCardId(Integer.parseInt(value));
                } else if (qName.equals("customerId")) {
                    debitCard.setCustomerId(Integer.parseInt(value));
                } else if (qName.equals("cardNumber")) {
                    debitCard.setCardNumber(value);
                } else if (qName.equals("expirationDate")) {
                    // Parse the date using SimpleDateFormat or any other date parsing method
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date expirationDate = dateFormat.parse(value);
                        debitCard.setExpirationDate(expirationDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (loans != null) {
                Loan loan = loans.get(loans.size() - 1);
                if (qName.equals("loanId")) {
                    loan.setLoanId(Integer.parseInt(value));
                } else if (qName.equals("customerId")) {
                    loan.setCustomerId(Integer.parseInt(value));
                } else if (qName.equals("loanType")) {
                    loan.setLoanType(value);
                } else if (qName.equals("loanAmount")) {
                    loan.setLoanAmount(Double.parseDouble(value));
                } else if (qName.equals("interestRate")) {
                    loan.setInterestRate(Double.parseDouble(value));
                } else if (qName.equals("startDate")) {
                    // Parse the date using SimpleDateFormat or any other date parsing method
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date startDate = dateFormat.parse(value);
                        loan.setStartDate(startDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (qName.equals("endDate")) {
                    // Parse the date using SimpleDateFormat or any other date parsing method
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date endDate = dateFormat.parse(value);
                        loan.setEndDate(endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            isParsingValue = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isParsingValue) {
            dataBuffer.append(ch, start, length);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public List<DebitCard> getDebitCards() {
        return debitCards;
    }

    public List<Loan> getLoans() {
        return loans;
    }
}





