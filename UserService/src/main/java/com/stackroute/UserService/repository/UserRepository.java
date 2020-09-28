package com.stackroute.UserService.repository;

import com.stackroute.UserService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByAccountNumber(String accountNum);
    User findByUsername(String username);
}
