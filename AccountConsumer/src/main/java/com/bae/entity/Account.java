package com.bae.entity;

public class Account {
    
	private Long accountId;
    private String firstName;
    private String lastName;
    private String accountNumber;
    
    public Account() {
    	super();
    }
    
    public Account (Long accountId, String firstName, String lastName, String accountNumber) {
    	this.accountId=accountId;
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.accountNumber=accountNumber;
    }
	
    
    public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




}
