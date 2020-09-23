package com.inovareti.rpontobackend.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.inovareti.rpontobackend.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbservice;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dbStrategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		System.out.println("*******************RUNNING IN DEV PROFILE**************");
		if(!"create".equals(dbStrategy)) {
			System.out.println("Banco não recriado!");
			return false;
		}
		System.out.println("Banco recriado!");
		dbservice.instantiateTestDatabase();
		return true;
	}
}
