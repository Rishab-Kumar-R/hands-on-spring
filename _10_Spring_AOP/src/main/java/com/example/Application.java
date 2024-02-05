package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.aop.business.BusinessService1;
import com.example.aop.business.BusinessService2;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private BusinessService1 businessService1;
	private BusinessService2 businessService2;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public Application(BusinessService1 businessService1, BusinessService2 businessService2) {
		this.businessService1 = businessService1;
		this.businessService2 = businessService2;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("BusinessService1 - Max value: {}", businessService1.calculateMax());
		logger.info("BusinessService2 - Sorted data: {}", businessService2.sortByNathuralOrder());
	}
}
