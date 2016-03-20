package com.springboot.app.services;

import com.springboot.app.model.Account;

public interface AccountService {
	Account findByUsername(String username);
}
