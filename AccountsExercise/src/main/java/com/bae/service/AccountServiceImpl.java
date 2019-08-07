package com.bae.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bae.entity.Account;
import com.bae.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepo;
	private RestTemplate restTemplate;
	private JmsTemplate jmsTemplate;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepo,RestTemplate restTemplate,JmsTemplate jmsTemplate) {
		this.accountRepo = accountRepo;
		this.restTemplate = restTemplate;
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public Collection<Account> getAll() {
		return accountRepo.findAll();
	}

	@Override
	public Optional<Account> getAccount(Long id) {
		return accountRepo.findById(id);
	}

	@Override
	public String createAccount(Account account) {
		
		ResponseEntity<String> exchangeAccountNum = restTemplate.exchange(
				"http://localhost:8082/accountNum/", 
				HttpMethod.GET, null, String.class);
		
		String accountNum = exchangeAccountNum.getBody();
		
		account.setAccountNumber(accountNum);
		
		ResponseEntity<String> exchangePrize = restTemplate.exchange(
				"http://localhost:8081/prizeGen/" + accountNum, 
				HttpMethod.GET, null, String.class);
		
		accountRepo.save(account);		
		
		jmsTemplate.convertAndSend("AccountQueue", account);
			
		return exchangePrize.getBody();

	}
	

	@Override
	public String deleteAccount(Long id) {

		if (accountRepo.existsById(id)) {
			accountRepo.deleteById(id);
			return ("Account Deleted.");
		} else {
			return ("Account not found.");
		}
	}

	@Override
	public String updateAccount(Account account) {

		if (accountRepo.existsById(account.getId())) {
			accountRepo.save(account);
			return ("Account Updated.");
		} else {
			return ("Account not found.");
		}

	}


}
