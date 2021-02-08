package com.essentials.springweb.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WebApplication {

	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(WebApplication.class);
		int count = 0;
		ApplicationContext appContext = SpringApplication.run(WebApplication.class, args);

		for(String name: appContext.getBeanDefinitionNames()) {
			// logger.info("Name of the Bean: " + name);
			count ++;
		}

		logger.info("Loaded " + count + " beans!!");
	}

}
