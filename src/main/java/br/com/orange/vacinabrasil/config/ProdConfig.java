package br.com.orange.vacinabrasil.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

	// @Autowired
	// private DBService dbService;

	@Bean
	public boolean instanciateDataBase() throws ParseException {

		// dbService.instanciateDatabase();
		return true;
	}

}
