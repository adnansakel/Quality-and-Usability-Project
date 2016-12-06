package com.studyproject.tuberlin.bingoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.studyproject.tuberlin.bingoapp.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.studyproject.tuberlin.bingoapp.entity"})
@ComponentScan
public class Application {
	public static void main(String[] args) 
	{ 
		SpringApplication.run(Application.class, args); 
	} 
}
