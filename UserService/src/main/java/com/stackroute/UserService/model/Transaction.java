package com.stackroute.UserService.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Transaction {

    private int transactionId;
    private String accountTransferredTo;
    private Double amountTransferred;
    private String currencyUsed;
    private Date dateOfTransfer = new Date();
    private String transactionCategory;
    private String username;
    private String status;

    public Transaction() {
    }

    public Transaction(int transactionId, String accountTransferredTo, Double amountTransferred,
                       String currencyUsed, Date dateOfTransfer, String transactionCategory, String username, String status) {
        this.transactionId = transactionId;
        this.accountTransferredTo = accountTransferredTo;
        this.amountTransferred = amountTransferred;
        this.currencyUsed = currencyUsed;
        this.dateOfTransfer = dateOfTransfer;
        this.transactionCategory = transactionCategory;
        this.username = username;
        this.status= status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountTransferredTo() {
        return accountTransferredTo;
    }

    public void setAccountTransferredTo(String accountTransferredTo) {
        this.accountTransferredTo = accountTransferredTo;
    }

    public Double getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred(Double amountTransferred) {
        this.amountTransferred = amountTransferred;
    }

    public String getCurrencyUsed() {
        return currencyUsed;
    }

    public void setCurrencyUsed(String currencyUsed) {
        this.currencyUsed = currencyUsed;
    }

    public Date getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(Date dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }


}