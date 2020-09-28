package com.stackroute.UserService.model;

import java.util.Date;

public class Conversion {

    private int conversionId;
    private String baseCurrency;
    private Double amountConverted;
    private String newCurrency;
    private Date dateOfTransfer;
    private Double newAmount;
    private String username;

    public Conversion() {
    }

    public Conversion(int conversionId, String baseCurrency, Double amountConverted, String newCurrency,
                      Date dateOfTransfer, Double newAmount, String username) {
        this.conversionId = conversionId;
        this.baseCurrency = baseCurrency;
        this.amountConverted = amountConverted;
        this.newCurrency = newCurrency;
        this.dateOfTransfer = dateOfTransfer;
        this.newAmount = newAmount;
        this.username = username;
    }

    public int getConversionId() {
        return conversionId;
    }

    public void setConversionId(int conversionId) {
        this.conversionId = conversionId;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Double getAmountConverted() {
        return amountConverted;
    }

    public void setAmountConverted(Double amountConverted) {
        this.amountConverted = amountConverted;
    }

    public String getNewCurrency() {
        return newCurrency;
    }

    public void setNewCurrency(String newCurrency) {
        this.newCurrency = newCurrency;
    }

    public Date getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(Date dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public Double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(Double newAmount) {
        this.newAmount = newAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
