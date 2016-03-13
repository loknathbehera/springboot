package com.springboot.app.api;

import java.util.Collection;
import java.util.concurrent.Future;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.Greeting;
import com.springboot.app.services.EmailService;
import com.springboot.app.services.GreetingService;

@RestController
public class GreetingController extends BaseController {

	@Autowired
	GreetingService greetingService;

	@Autowired
	EmailService emailService;

	@RequestMapping(value = "api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() throws Exception {

		Collection<Greeting> greetings = greetingService.findAll();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}

	@RequestMapping(value = "api/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable(value = "id") Long id) throws NoResultException {
		Greeting greeting = greetingService.findOne(id);
		if (greeting == null) {
			throw new NoResultException("Not result");
		}
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	@RequestMapping(value = "api/greetings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) throws Exception {

		Greeting savedGreeting = greetingService.create(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}

	@RequestMapping(value = "api/greetings/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) throws Exception {
		Greeting updatedGreeting = greetingService.update(greeting);
		if (updatedGreeting == null) {
			throw new Exception();
		}

		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
	}

	@RequestMapping(value = "api/greetings/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable(value = "id") Long id, @RequestBody Greeting greeting)
			throws Exception {

		greetingService.delete(id);

		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "api/greetings/{id}/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> sendGreeting(@PathVariable(value = "id") Long id,
			@RequestParam(value = "wait", defaultValue = "false") boolean waitForAsyncResult) {

		logger.info("> sendGreeting");

		Greeting greeting = null;

		try {
			greeting = greetingService.findOne(id);
			if (greeting == null) {
				logger.info("< sendGreeting");
				return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
			}

			if (waitForAsyncResult) {
				Future<Boolean> asyncResponse = emailService.sendAsyncWithResult(greeting);
				boolean emailSent = asyncResponse.get();
				logger.info("- greeting email sent? {}", emailSent);
			} else {
				emailService.sendAsync(greeting);
			}
		} catch (Exception e) {
			logger.error("A problem occurred sending the Greeting.", e);
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< sendGreeting");
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);

	}

}