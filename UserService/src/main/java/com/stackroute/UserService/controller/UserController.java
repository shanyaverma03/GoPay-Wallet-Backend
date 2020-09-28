package com.stackroute.UserService.controller;

import com.stackroute.UserService.exceptions.UserAlreadyExistsException;
import com.stackroute.UserService.exceptions.UserNotFoundException;
import com.stackroute.UserService.model.Conversion;
import com.stackroute.UserService.model.Transaction;
import com.stackroute.UserService.model.User;
import com.stackroute.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {


    private UserService userService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            User createdUser = userService.registerUser(user);
            responseEntity = new ResponseEntity(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable("id") String userId) {
        try {
            User createdUser = userService.updateUser(userId, user);
            responseEntity = new ResponseEntity("Updated", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping("currencies/{id}")
    public ResponseEntity getAllCurrencies(@PathVariable("id") String userId) {
        try {
            HashMap<String, Double> currencies = userService.currenciesById(userId);
            responseEntity = new ResponseEntity(currencies, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        try {
            List<User> list = userService.getAllUsers();
            responseEntity = new ResponseEntity(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("/add/{amount}/{id}")
    public ResponseEntity addAmount(@PathVariable("amount") Double amount, @PathVariable("id") String username) {
        try {
            HashMap<String, Double> currencies = userService.addAmountInwallet(username, amount);
            responseEntity = new ResponseEntity(currencies, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/getConversions/{id}")
    public ResponseEntity getConversions( @PathVariable("id") String username) {
        try {
            List<Conversion> conversions = userService.getConversions(username);
            responseEntity = new ResponseEntity(conversions, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PostMapping("/convert/{base}/{amountConv}/{newCurr}/{newAmount}/{id}")
    public ResponseEntity convertCurrency(@RequestBody Conversion conversion, @PathVariable("base") String baseCurrency,
                                          @PathVariable("amountConv") Double amountConv, @PathVariable("newCurr") String newCurr,
                                          @PathVariable("newAmount") Double newAmount, @PathVariable("id") String username) {
        try {
            List<Conversion> conversions= userService.convertCurrency(conversion);
            responseEntity = new ResponseEntity(conversions, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //Controllers related to payments

    @PostMapping("/saveTransaction")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        try {
            boolean created = userService.createTransaction(transaction);
            if (created) {
                responseEntity = new ResponseEntity("Transaction successful", HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("/all/{userId}")
    public ResponseEntity getAllTransactions(@PathVariable("userId") String userId) {
        try {
            List<Transaction> list = userService.getAllTransactionsByUserId(userId);
            responseEntity = new ResponseEntity(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
