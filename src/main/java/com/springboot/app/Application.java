package com.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class Application 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class);
    }
    
    @Bean
    public CacheManager cacheManager(){
    	ConcurrentMapCacheManager cmc=new ConcurrentMapCacheManager("greeting");
    	return cmc;
    }
}
