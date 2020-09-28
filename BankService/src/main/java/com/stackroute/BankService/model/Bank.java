package com.stackroute.BankService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Bank {

    @Id
    private String bankId;
    private String name;
    private String accountNumber;
    private double accountBalance;

    public Bank() {
    }

    public Bank(String bankId, String name, String accountNumber, double accountBalance) {
        this.bankId = bankId;
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId='" + bankId + '\'' +
                ", name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", AccountBalance='" + accountBalance + '\'' +
                '}';
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
