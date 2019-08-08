package com.bae.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bae.entity.Account;
import com.bae.repository.AccountRepository;
import com.bae.util.Constants;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTests {
	
	@InjectMocks
	AccountServiceImpl service;

	@Mock
	AccountRepository repo;
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	JmsTemplate jmsTemplate;
	
	@Test
	public void getAllTest() {
		
		List<Account> MOCK_COLLECTION = new ArrayList<>();
		
		MOCK_COLLECTION.add(Constants.MOCK_ACCOUNT_1);
		MOCK_COLLECTION.add(Constants.MOCK_ACCOUNT_2);
		
		Mockito.when(repo.findAll()).thenReturn((List<Account>) MOCK_COLLECTION);
		
		assertEquals(MOCK_COLLECTION, service.getAll());
		
		Mockito.verify(repo).findAll();

	}
	
	@Test
	public void getAccountTest() {
		
		Mockito.when(repo.findById((long)1)).thenReturn(Optional.of(Constants.MOCK_ACCOUNT_1));
		
		assertEquals(Optional.of(Constants.MOCK_ACCOUNT_1), service.getAccount((long)1));
		
		Mockito.verify(repo).findById((long)1);
	}
	
	@Test
	public void createAccountTest() {
		
        ResponseEntity<String> accountNum = new ResponseEntity<String>("a2659390674", HttpStatus.OK);
		
        Mockito.when(restTemplate.exchange("http://account-number-generator:8082/accountNum/",
                HttpMethod.GET, null, String.class)).thenReturn(accountNum);
        
        ResponseEntity<String> prize = new ResponseEntity<String>("No Prize.", HttpStatus.OK);
        
        
        Mockito.when(restTemplate.exchange("http://prize-generator:8081/prizeGen/a2659390674",
                HttpMethod.GET, null, String.class)).thenReturn(prize);
        
		Mockito.when(repo.save(Constants.MOCK_ACCOUNT_1)).thenReturn(Constants.MOCK_ACCOUNT_1);
		
		assertEquals("No Prize.", service.createAccount(Constants.MOCK_ACCOUNT_1));
		
		Mockito.verify(repo).save(Constants.MOCK_ACCOUNT_1);
	}
	
	
	@Test
	public void deleteAccountSuccessTest() {
		
		Mockito.when(repo.existsById(Constants.MOCK_ACCOUNT_1.getId())).thenReturn(true);
	
		assertEquals(Constants.MOCK_DELETE_SUCCESS_RESPONSE, service.deleteAccount((long)1));
		
		Mockito.verify(repo).deleteById((long)1);	
	}
	
	@Test
	public void deleteAccountFailureTest() {
		
		Mockito.when(repo.existsById(Constants.MOCK_ACCOUNT_1.getId())).thenReturn(false);
		
		assertEquals(Constants.MOCK_FAILURE_RESPONSE, service.deleteAccount((long)1));
		
		Mockito.verify(repo).existsById((long)1);	
	}

	
	@Test
	public void updateAccountSuccessTest() {
		
		Mockito.when(repo.existsById(Constants.MOCK_ACCOUNT_1.getId())).thenReturn(true);
		
		Mockito.when(repo.save(Constants.MOCK_ACCOUNT_1)).thenReturn(Constants.MOCK_ACCOUNT_1);
	
		assertEquals(Constants.MOCK_UPDATE_SUCCESS_RESPONSE, service.updateAccount(Constants.MOCK_ACCOUNT_1));
		
		Mockito.verify(repo).existsById(Constants.MOCK_ACCOUNT_1.getId());	
		Mockito.verify(repo).save(Constants.MOCK_ACCOUNT_1);		
	
	}
	
	@Test
	public void updateAccountFailureTest() {
		
		Mockito.when(repo.existsById(Constants.MOCK_ACCOUNT_1.getId())).thenReturn(false);
		
		assertEquals(Constants.MOCK_FAILURE_RESPONSE, service.updateAccount(Constants.MOCK_ACCOUNT_1));
					
		Mockito.verify(repo).existsById(Constants.MOCK_ACCOUNT_1.getId());	
	
	}
}
