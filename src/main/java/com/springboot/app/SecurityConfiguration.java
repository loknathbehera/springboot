package com.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.app.security.AccountAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private AccountAuthenticationProvider accountAuthenticationProvider;
	
	@Bean 
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth){
		auth.authenticationProvider(accountAuthenticationProvider);
	}
	
	  @Configuration
	    @Order(1)
	    public static class ApiWebSecurityConfigurerAdapter
	            extends WebSecurityConfigurerAdapter {

	        @Override
	        protected void configure(HttpSecurity http) throws Exception {

	            // @formatter:off
	            
	            http
	            .csrf().disable()
	            .antMatcher("/api/**")
	              .authorizeRequests()
	                .anyRequest().hasRole("USER")
	            .and()
	            .httpBasic()
	            .and()
	            .sessionManagement()
	              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	            
	            // @formatter:on

	        }

	    }

	    /**
	     * This inner class configures the WebSecurityConfigurerAdapter instance for
	     * the Spring Actuator web service context paths.
	     * 
	     * @author Matt Warman
	     */
	    @Configuration
	    @Order(2)
	    public static class ActuatorWebSecurityConfigurerAdapter
	            extends WebSecurityConfigurerAdapter {

	        @Override
	        protected void configure(HttpSecurity http) throws Exception {

	            // @formatter:off
	            
	            http
	            .csrf().disable()
	            .antMatcher("/actuators/**")
	              .authorizeRequests()
	                .anyRequest().hasRole("SYSADMIN")
	            .and()
	            .httpBasic()
	            .and()
	            .sessionManagement()
	              .sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
	            
	            // @formatter:on

	        }

	    }
	
}
