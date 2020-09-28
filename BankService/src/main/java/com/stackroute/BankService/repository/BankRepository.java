package com.stackroute.BankService.repository;

import com.stackroute.BankService.model.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankRepository extends MongoRepository<Bank, String> {

    Bank findByNameAndAccountNumber(String name, String accountNumber);
}
