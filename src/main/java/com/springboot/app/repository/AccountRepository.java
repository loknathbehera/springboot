package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	public Account findByUsername(String username);
}
