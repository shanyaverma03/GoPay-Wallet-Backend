package com.stackroute.UserService.service;

import com.stackroute.UserService.exceptions.TransactionNotCreatedException;
import com.stackroute.UserService.exceptions.UserAlreadyExistsException;
import com.stackroute.UserService.exceptions.UserNotFoundException;
import com.stackroute.UserService.model.Conversion;
import com.stackroute.UserService.model.Transaction;
import com.stackroute.UserService.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    User registerUser(User user) throws UserAlreadyExistsException;

    User updateUser(String userId, User user) throws UserNotFoundException;



    List<User> getAllUsers();

    HashMap<String, Double> addAmountInwallet(String username,Double amount);

    HashMap<String, Double> currenciesById(String username) throws  UserNotFoundException;

    List<Conversion> convertCurrency(Conversion conversion) throws UserNotFoundException ;

    List<Conversion> getConversions(String username) throws UserNotFoundException ;

    //Services relaed to transactions
    boolean createTransaction(Transaction transaction) throws TransactionNotCreatedException;


    List<Transaction> getAllTransactionsByUserId(String userId);

}
