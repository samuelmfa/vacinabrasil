package br.com.orange.vacinabrasil.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Usuario usuario1 = new Usuario("Samuel P. Costa", "samuel@gmail.com", "47206606067", sdf.parse("27/11/1982"));
		Usuario usuario2 = new Usuario("Ana Maria Braga", "anamaria@gmail.com", "53778407040", sdf.parse("01/04/1949"));
		Usuario usuario3 = new Usuario("Alcides Alves Moreira", "cidmoreira@globo.com", "62097942032",
				sdf.parse("29/09/1927"));

		AplicacaoVacina aplic1 = new AplicacaoVacina("Oxford/AstraZeneca", sdf.parse("20/02/2021"), usuario1);
		AplicacaoVacina aplic2 = new AplicacaoVacina("CoronaVac", sdf.parse("21/01/2021"), usuario2);
		AplicacaoVacina aplic3 = new AplicacaoVacina("CoronaVac", sdf.parse("22/01/2021"), usuario3);

		usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3));
		aplicacaoRepository.saveAll(Arrays.asList(aplic1, aplic2, aplic3));

	}

}
