package com.springboot.app.services;

import java.util.Collection;

import com.springboot.app.model.Greeting;

public interface GreetingService {

	Collection<Greeting>findAll();
	
	Greeting findOne(Long id);
	
	Greeting create(Greeting greeting);
	
	Greeting update(Greeting greeting);
	
	void delete(Long id);
	
}
