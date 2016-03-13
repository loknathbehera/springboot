package com.springboot.app.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.model.Greeting;
import com.springboot.app.repository.GreetingRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GreetingServiceBean implements GreetingService {

	@Autowired
	private GreetingRepository greetingRepository;

	@Autowired
	private CounterService counterService;

	@Override
	public Collection<Greeting> findAll() {

		counterService.increment("Greeting.invoked.findAll");

		return greetingRepository.findAll();
	}

	@Override
	@Cacheable(value = "greeting", key = "#id")
	public Greeting findOne(Long id) {
		counterService.increment("Greeting.invoked.findOne");
		return greetingRepository.findOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greeting", key = "#result.id")
	public Greeting create(Greeting greeting) {

		counterService.increment("Greeting.invoked.create");
		if (greeting.getId() != null) {
			return null;
		}
		Greeting savedGreeting = greetingRepository.save(greeting);
		return savedGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greeting", key = "#greeting.id")
	public Greeting update(Greeting greeting) {
		counterService.increment("Greeting.invoked.update");
		Greeting persistetGreeting = findOne(greeting.getId());
		if (persistetGreeting == null) {
			return null;
		}

		Greeting updatedGreeting = greetingRepository.save(greeting);

		if (greeting.getId() == 3) {
			throw new RuntimeException("Bang Bang");
		}

		return updatedGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value = "greeting", key = "#id", allEntries = true)
	public void delete(Long id) {
		counterService.increment("Greeting.invoked.delete");
		Greeting greeting = greetingRepository.findOne(id);
		greetingRepository.delete(greeting);

	}

}
