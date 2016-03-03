package com.springboot.app.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.Greeting;

@RestController
public class GreetingController {

	private static BigDecimal nextId;
	private static Map<BigDecimal, Greeting> greetingMap;

	private static Greeting save(Greeting greeting) {
		if (greetingMap == null) {
			greetingMap = new HashMap<BigDecimal, Greeting>();
			nextId = BigDecimal.ONE;
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

	@RequestMapping(value = "api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {

		Collection<Greeting> greetings = greetingMap.values();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}
}
