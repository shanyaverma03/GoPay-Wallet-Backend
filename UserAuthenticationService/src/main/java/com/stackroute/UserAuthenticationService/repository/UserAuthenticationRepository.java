package com.stackroute.UserAuthenticationService.repository;

import com.stackroute.UserAuthenticationService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticationRepository extends MongoRepository<User, String> {

    User findByUsernameAndUserPassword(String userId, String password);
}
