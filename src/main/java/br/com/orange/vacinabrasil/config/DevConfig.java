package br.com.orange.vacinabrasil.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

	// @Autowired
	// private DBService dbService;

	@Bean
	public boolean instanciateDataBase() throws ParseException {

		// dbService.instanciateDatabase();
		return true;
	}

}
