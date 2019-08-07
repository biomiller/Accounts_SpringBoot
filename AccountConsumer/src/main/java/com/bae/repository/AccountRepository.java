package com.bae.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bae.entity.Account;

public interface AccountRepository extends MongoRepository<Account, String> {}
