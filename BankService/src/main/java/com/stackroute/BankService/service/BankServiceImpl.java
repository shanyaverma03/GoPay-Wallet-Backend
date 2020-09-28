package com.stackroute.BankService.service;

import com.stackroute.BankService.exception.AccountNotFoundException;
import com.stackroute.BankService.model.Bank;
import com.stackroute.BankService.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public boolean saveAccount(Bank bank) {
        Bank optional = bankRepository.findByNameAndAccountNumber(bank.getName(), bank.getAccountNumber());
        //Bank bank1 = null;
        boolean ans;
        if (optional != null) {
            ans = false;
        } else {
            ans = true;
            bank.setBankId(bank.getAccountNumber());
            bankRepository.save(bank);
        }
        return ans;
    }

    @Override
    public Bank getBankById(String bankId) throws AccountNotFoundException {
        Bank bank;
        try {
            bank = bankRepository.findById(bankId).get();
        } catch (NoSuchElementException e) {
            throw new AccountNotFoundException("Account Not Found");
        }
        return bank;
    }


}
