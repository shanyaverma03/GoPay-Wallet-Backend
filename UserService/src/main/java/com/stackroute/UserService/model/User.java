package com.stackroute.UserService.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Document
public class User {

    @Id
    private ObjectId userId;
    private String name;
    private String contact;
    private String baseCurrency;
    private String bankName;
    private String accountNumber;
    private String accountType;
    private String username;
    private String userPassword;
    private List<Transaction> transactions = new ArrayList<>();
    private HashMap<String, Double> currencies;
    private  List<Conversion> conversions= new ArrayList<>();


    public User() {
    }

    public User(ObjectId userId, String name, String contact, String baseCurrency, String bankName, String accountNumber, String accountType, String username, String userPassword,
                List<Transaction> transactions, HashMap<String, Double> currencies, List<Conversion> conversions) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.baseCurrency = baseCurrency;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.username = username;
        this.userPassword = userPassword;
        this.transactions = transactions;
        this.currencies = currencies;
        this.conversions = conversions;
    }

    public List<Conversion> getConversions() {
        return conversions;
    }

    public void setConversions(List<Conversion> conversions) {
        this.conversions = conversions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public HashMap<String, Double> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(HashMap<String, Double> currencies) {
        this.currencies = currencies;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
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

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
