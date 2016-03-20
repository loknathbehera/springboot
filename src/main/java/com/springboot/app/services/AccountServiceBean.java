package com.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.model.Account;
import com.springboot.app.repository.AccountRepository;

@Service
public class AccountServiceBean implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account findByUsername(String username) {
		Account account = accountRepository.findByUsername(username);
		return account;
	}

}
