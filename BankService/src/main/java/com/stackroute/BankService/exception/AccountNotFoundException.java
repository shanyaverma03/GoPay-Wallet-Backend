package com.stackroute.BankService.exception;

public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(String message){
        super(message);
    }
}
