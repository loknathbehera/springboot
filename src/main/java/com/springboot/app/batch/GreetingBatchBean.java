package com.springboot.app.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.app.model.Greeting;
import com.springboot.app.services.GreetingService;

@Component
public class GreetingBatchBean {

	@Autowired
	GreetingService greetingService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron="0,2 * * * * *")
	public void cronJob() {

		log.info("<<<<<<<");

		Collection<Greeting> greetings = greetingService.findAll();

		log.info("No of greeting object store {}", greetings.size());
		
		log.info(">>>>>>>");

	}
	
	@Scheduled(initialDelay=5000,fixedRate=10000)
	public void intialDelay(){
		
		log.info(">>initial delay");
		
		long pause=5000;
		long currentTime=System.currentTimeMillis();
		
		do{
			if(pause+currentTime<System.currentTimeMillis()){
				break;
			}
		}while(true);
		
		log.info("Procession time was {}",pause/1000);
		
		log.info(">>initial delay");
		
	}
	
	@Scheduled(initialDelay=5000,fixedDelay=10000)
	public void fixedDelay(){
		
		log.info(">>fixed Delay");
		
		long pause=5000;
		long currentTime=System.currentTimeMillis();
		
		do{
			if(pause+currentTime<System.currentTimeMillis()){
				break;
			}
		}while(true);
		
		log.info("Procession time was {}",pause/1000);
		
		log.info(">>fixed Delay");
		
	}
	
	
}
