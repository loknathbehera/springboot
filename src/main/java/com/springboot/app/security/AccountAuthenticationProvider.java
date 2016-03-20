package com.springboot.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	AccountUserDetailsService accountUserDetailsService;

	@Autowired
	PasswordEncoder passowordEncoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {

		if (token.getCredentials() == null || userDetails.getPassword() == null) {
			throw new BadCredentialsException("Bad credentials");
		}

		if (!passowordEncoder.matches((String) token.getCredentials(), userDetails.getPassword())) {
			throw new BadCredentialsException("Bad credentials");
		}

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {

		UserDetails userDetails = accountUserDetailsService.loadUserByUsername(username);
		return userDetails;
	}

}
