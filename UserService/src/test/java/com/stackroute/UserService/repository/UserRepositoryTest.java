package com.stackroute.UserService.repository;

import com.stackroute.UserService.model.Conversion;
import com.stackroute.UserService.model.Transaction;
import com.stackroute.UserService.model.User;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    User user;
    Conversion conversion;
    Transaction transaction;

    @Before //1
    public void setUp(){

        transaction= new Transaction(11, "75674", 1000.0, "inr",
                new Date(), "food", "shanya1", "sent");
        ArrayList<Transaction> transactions= new ArrayList<>();
        transactions.add(transaction);

        conversion= new Conversion(12, "inr", 500.0, "cny", new Date(),
                100.0, "shanya1");
        ArrayList<Conversion> conversions= new ArrayList<>();
        conversions.add(conversion);

        HashMap<String, Double> currencies= new HashMap<>();
        currencies.put("inr", 5000.0);
        currencies.put("cny", 5000.0);
        currencies.put("usd", 5000.0);
        currencies.put("euro", 5000.0);
        user = new User(new ObjectId(), "shanya", "995886","inr","sbi","8878339",
                "savings","shanya","shanya1",transactions,
                currencies, conversions);

    }

    @After //3
    public void tearDown(){

        user = null;
        transaction = null;
        conversion= null;
        userRepository.deleteAll();
    }

    @Test //2
    public void testSaveUser(){

        userRepository.insert(user); //Track1
        User fetchUser = userRepository.findByUsername(user.getUsername()); //Track1
        //ft.Track1 .equals(Track1)
        Assert.assertEquals(fetchUser.getAccountNumber() ,user.getAccountNumber());
    }

}
