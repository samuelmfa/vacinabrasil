package br.com.orange.vacinabrasil.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.orange.vacinabrasil.entities.AplicacaoVacina;
import br.com.orange.vacinabrasil.entities.Usuario;
import br.com.orange.vacinabrasil.repository.AplicacaoVacinaRepository;
import br.com.orange.vacinabrasil.repository.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	AplicacaoVacinaRepository aplicacaoRepository;

	public void instanciateDatabase() throws ParseException {		

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);

		Usuario usuario1 = new Usuario("Samuel P. Costa", "samuel@gmail.com", "47206606067", sdf.parse("1982-11-27T03:00:00.000Z"));
		Usuario usuario2 = new Usuario("Ana Maria Braga", "anamaria@gmail.com", "53778407040", sdf.parse("1949-04-01T03:00:00.000Z"));
		Usuario usuario3 = new Usuario("Alcides Alves Moreira", "cidmoreira@globo.com", "62097942032",
				sdf.parse("1927-09-29T03:00:00.000Z"));

		AplicacaoVacina aplic1 = new AplicacaoVacina("Oxford/AstraZeneca", sdf.parse("2021-02-20T03:00:00.000Z"), usuario1);
		AplicacaoVacina aplic2 = new AplicacaoVacina("CoronaVac", sdf.parse("2021-01-21T03:00:00.000Z"), usuario2);
		AplicacaoVacina aplic3 = new AplicacaoVacina("CoronaVac", sdf.parse("2021-01-22T03:00:00.000Z"), usuario3);

		usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3));
		aplicacaoRepository.saveAll(Arrays.asList(aplic1, aplic2, aplic3));

	}

}
