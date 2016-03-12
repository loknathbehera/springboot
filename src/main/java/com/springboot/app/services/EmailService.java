package com.springboot.app.services;

import java.util.concurrent.Future;

import com.springboot.app.model.Greeting;

public interface EmailService {

	Boolean send(Greeting greeting);

	void sendAsync(Greeting greeting);

	Future<Boolean> sendAsyncWithResult(Greeting greeting);
	

}
