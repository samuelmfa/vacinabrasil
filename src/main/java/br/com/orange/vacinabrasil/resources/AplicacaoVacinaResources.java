package br.com.orange.vacinabrasil.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.orange.vacinabrasil.entities.AplicacaoVacina;
import br.com.orange.vacinabrasil.entities.Usuario;
import br.com.orange.vacinabrasil.service.AplicacaoVacinaService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/vacinas")
public class AplicacaoVacinaResources {

	@Autowired
	AplicacaoVacinaService service;

	@GetMapping
	public ResponseEntity<List<AplicacaoVacina>> findAll() {
		List<AplicacaoVacina> aplicacoes = service.findAll();
		return ResponseEntity.ok().body(aplicacoes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AplicacaoVacina> findById(@PathVariable("id") Long id) throws ObjectNotFoundException {
		AplicacaoVacina obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<AplicacaoVacina> findByUsuarioId(@PathVariable("id") Long id) throws ObjectNotFoundException {
		AplicacaoVacina obj = service.findByUsuarioId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping()
	public ResponseEntity<Void> inserirAplicacao(@RequestBody AplicacaoVacina aplicacao) throws ObjectNotFoundException {
		AplicacaoVacina novaAplicacao = service.insert(aplicacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaAplicacao.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping()
	public ResponseEntity<Void> update(@RequestBody AplicacaoVacina obj) throws ObjectNotFoundException {
		AplicacaoVacina aplicacao = service.update(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aplicacao.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable("id") Long id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

}
