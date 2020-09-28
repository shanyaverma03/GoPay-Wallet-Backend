package com.stackroute.BankService.controller;

import com.stackroute.BankService.exception.AccountNotFoundException;
import com.stackroute.BankService.model.Bank;
import com.stackroute.BankService.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/bank")
public class BankController {

    private BankService bankService;
    private ResponseEntity responseEntity;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/save")
    public ResponseEntity saveAccount(@RequestBody Bank bank) {
        try {
            Boolean createdAcc = bankService.saveAccount(bank);
            responseEntity = new ResponseEntity(createdAcc, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/{bankId}")
    public ResponseEntity getAccount(@PathVariable("bankId") String bankId) {
        try {
            Bank bank = bankService.getBankById(bankId);
            responseEntity = new ResponseEntity(bank, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/balance/{bankId}")
    public ResponseEntity getBalance(@PathVariable("bankId") String bankId) {
        try {
            Bank bank = bankService.getBankById(bankId);
            responseEntity = new ResponseEntity(bank.getAccountBalance(), HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping("/{bankId}/{amount}")
    public ResponseEntity updateBalance(@PathVariable("bankId") String bankId, @PathVariable("amount") double amount) {
        try {
            Bank bank = bankService.getBankById(bankId);
            double balance = bank.getAccountBalance();
            double newBalance = balance - amount;
            if (newBalance > 0) {
                bank.setAccountBalance(newBalance);
                responseEntity = new ResponseEntity(newBalance, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity("Insufficient Balance", HttpStatus.CONFLICT);
            }
        } catch (AccountNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
