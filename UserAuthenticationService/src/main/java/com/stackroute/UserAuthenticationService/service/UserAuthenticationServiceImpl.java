package com.stackroute.UserAuthenticationService.service;

import com.stackroute.UserAuthenticationService.exceptions.UserAlreadyExistsException;
import com.stackroute.UserAuthenticationService.exceptions.UserNotFoundException;
import com.stackroute.UserAuthenticationService.model.User;
import com.stackroute.UserAuthenticationService.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private UserAuthenticationRepository userAuthenticationRepository;


    @Autowired
    public UserAuthenticationServiceImpl(UserAuthenticationRepository userAuthenticationRepository) {
        this.userAuthenticationRepository = userAuthenticationRepository;
    }


    @Override
    public boolean validateUser(String userName, String password) throws UserNotFoundException {
        User user = userAuthenticationRepository.findByUsernameAndUserPassword(userName, password);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        }
        return true;
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        User createdUser = userAuthenticationRepository.findByUsernameAndUserPassword(user.getUsername(), user.getUserPassword());
        if (createdUser != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        user.setUserId(user.getUsername());
        HashMap<String, Double> map = new HashMap<>();
        map.put("inr" , 0.0);
        map.put("euro" , 0.0);
        map.put("usd" , 0.0);
        map.put("cny" , 0.0);
        user.setCurrencies(map);


        User user1 = userAuthenticationRepository.save(user);
        System.out.println(user1.getCurrencies());
        return user1;
    }
}
