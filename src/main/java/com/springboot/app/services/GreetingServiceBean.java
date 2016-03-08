package com.springboot.app.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.app.model.Greeting;

@Service
public class GreetingServiceBean implements GreetingService {

	private static BigDecimal nextId;
	private static Map<BigDecimal, Greeting> greetingMap;

	public static boolean remove(Long id) {

		Greeting deletedGreeting = greetingMap.remove(id);
		if (deletedGreeting != null) {
			return true;
		} else {
			return false;
		}
	}

	private static Greeting save(Greeting greeting) {
		if (greetingMap == null) {
			greetingMap = new HashMap<BigDecimal, Greeting>();
			nextId = BigDecimal.ONE;
		}
		// updates.......

		if (greeting.getId() != null) {
			Greeting oldGreeting = greetingMap.get(greeting.getId());
			if (oldGreeting == null) {
				return null;
			}
			greetingMap.remove(greeting.getId());
			greetingMap.put(greeting.getId(), greeting);

			return greeting;
		}

		greeting.setId(nextId);
		nextId = nextId.add(BigDecimal.ONE);
		greetingMap.put(greeting.getId(), greeting);
		return greeting;
	}

	static {
		Greeting greeting1 = new Greeting();
		greeting1.setText("Hi Chiku");
		save(greeting1);

		Greeting greeting2 = new Greeting();
		greeting2.setText("Hi Chiku");
		save(greeting2);

	}

	@Override
	public Collection<Greeting> findAll() {
		return greetingMap.values();
	}

	@Override
	public Greeting findOne(Long id) {
		return greetingMap.get(id);
	}

	@Override
	public Greeting create(Greeting greeting) {

		Greeting savedGreeting = save(greeting);
		return savedGreeting;
	}

	@Override
	public Greeting update(Greeting greeting) {
		Greeting updatedGreeting = save(greeting);
		return updatedGreeting;
	}

	@Override
	public void delete(Long id) {

		remove(id);

	}

}
