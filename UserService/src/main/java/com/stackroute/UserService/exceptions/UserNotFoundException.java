package com.stackroute.UserService.exceptions;

public class UserNotFoundException extends  Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
