package com.stackroute.UserAuthenticationService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;


@Document
public class User {

    @Id
    private String userId;
    private String name;
    private String contact;
    private String baseCurrency;
    private String bankName;
    private String accountNumber;
    private String accountType;
    private String username;
    private String userPassword;
    private HashMap<String, Double> currencies;


    public User() {
    }

    public User(String userId, String name, String contact, String baseCurrency, String bankName, String accountNumber, String accountType,
                String username, String userPassword, HashMap<String, Double> currencies) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.baseCurrency = baseCurrency;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.username = username;
        this.userPassword = userPassword;
        this.currencies = currencies;
    }


    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public HashMap<String, Double> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(HashMap<String, Double> currencies) {
        this.currencies = currencies;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
