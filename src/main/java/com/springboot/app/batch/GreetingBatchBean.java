package com.springboot.app.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.app.model.Greeting;
import com.springboot.app.services.GreetingService;

@Profile("batch")
@Component
public class GreetingBatchBean {

	@Autowired
	GreetingService greetingService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron="${batch.Greeting.cronJob}")
	public void cronJob() {

		log.info("<<<<<<<");

		Collection<Greeting> greetings = greetingService.findAll();

		log.info("No of greeting object store {}", greetings.size());
		
		log.info(">>>>>>>");

	}
	
	@Scheduled(initialDelayString="${batch.Greeting.initialDelay}",fixedRateString="${batch.Greeting.fixedRate}")
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
	
	@Scheduled(initialDelayString="${batch.Greeting.initialDelay}",fixedDelayString
			="${batch.Greeting.fixedDelay}")
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
