package com.springboot.app.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.model.Greeting;
import com.springboot.app.repository.GreetingRepository;

@Service
public class GreetingServiceBean implements GreetingService {

	@Autowired
	private GreetingRepository greetingRepository;

	@Override
	public Collection<Greeting> findAll() {
		return greetingRepository.findAll();
	}

	@Override
	public Greeting findOne(Long id) {
		return greetingRepository.findOne(id);
	}

	@Override
	public Greeting create(Greeting greeting) {

		if (greeting.getId() != null) {
			return null;
		}
		Greeting savedGreeting = greetingRepository.save(greeting);
		return savedGreeting;
	}

	@Override
	public Greeting update(Greeting greeting) {
		Greeting persistetGreeting = findOne(greeting.getId());
		if (persistetGreeting == null) {
			return null;
		}

		Greeting updatedGreeting = greetingRepository.save(greeting);
		return updatedGreeting;
	}

	@Override
	public void delete(Long id) {

		Greeting greeting = greetingRepository.findOne(id);
		greetingRepository.delete(greeting);

	}

}
