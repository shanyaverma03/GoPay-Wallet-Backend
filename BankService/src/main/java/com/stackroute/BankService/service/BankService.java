package com.stackroute.BankService.service;

import com.stackroute.BankService.exception.AccountNotFoundException;
import com.stackroute.BankService.model.Bank;

public interface BankService {
    boolean saveAccount(Bank bank);

    Bank getBankById(String bankId) throws AccountNotFoundException;

}
