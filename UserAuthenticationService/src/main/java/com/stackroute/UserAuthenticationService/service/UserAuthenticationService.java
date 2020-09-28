package com.stackroute.UserAuthenticationService.service;

import com.stackroute.UserAuthenticationService.exceptions.UserAlreadyExistsException;
import com.stackroute.UserAuthenticationService.exceptions.UserNotFoundException;
import com.stackroute.UserAuthenticationService.model.User;

public interface UserAuthenticationService {

    boolean validateUser(String username, String password) throws UserNotFoundException;

    User createUser(User user) throws UserAlreadyExistsException;
}
