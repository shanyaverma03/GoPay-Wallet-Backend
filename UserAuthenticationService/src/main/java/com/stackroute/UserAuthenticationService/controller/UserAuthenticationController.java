package com.stackroute.UserAuthenticationService.controller;

import com.stackroute.UserAuthenticationService.exceptions.UserAlreadyExistsException;
import com.stackroute.UserAuthenticationService.model.User;
import com.stackroute.UserAuthenticationService.service.UserAuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/v1/auth")
public class UserAuthenticationController {

    private UserAuthenticationService userAuthenticationService;

    private ResponseEntity responseEntity;
    private Map<String, String> map = new HashMap<>();

    @Autowired
    public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        try {
            User createdUser = userAuthenticationService.createUser(user);
            responseEntity = new ResponseEntity(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody User user) {
        try {
            String jwtToken = getToken(user.getUsername(), user.getUserPassword());
            map.put("message", "User successfully logged in");
            map.put("token", jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
            map.put("token", null);
            return new ResponseEntity(map, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(map, HttpStatus.OK);
    }

    // Generate JWT token
    public String getToken(String username, String password) throws Exception {
        String jwtToken = "";
        if (username == null || password == null) {
            throw new Exception("Please send valid username or password");
        }
        //validate the user from db

        boolean flag = userAuthenticationService.validateUser(username, password);
        if (!flag) {
            throw new Exception("Invalid credentials");
        } else {
            jwtToken = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 300000))
                    .signWith(SignatureAlgorithm.HS256, "secretkey")
                    .compact();
        }
        return jwtToken;
    }

}
