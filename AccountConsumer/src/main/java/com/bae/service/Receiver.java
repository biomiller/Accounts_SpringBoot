package com.bae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.bae.entity.Account;
import com.bae.repository.AccountRepository;

@Service
public class Receiver {
	
	@Autowired
	private AccountRepository mongoRepo;
		
	@JmsListener(destination = "AccountQueue", containerFactory = "myFactory")
	public void receiveAccount(Account account) {
		String receiveAccount = "< Account Received >" + account.getAccountNumber() + ">";
		System.out.println(receiveAccount);
		mongoRepo.insert(account);

		
	}
	
	
		
	
}
