package br.com.orange.vacinabrasil.resources;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

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

import br.com.orange.vacinabrasil.entities.Usuario;
import br.com.orange.vacinabrasil.service.UsuarioService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResources {

	@Autowired
	UsuarioService service;

	@GetMapping()
	public ResponseEntity<List<Usuario>> findAll() throws ParseException {
		List<Usuario> usuarios = service.findAll();
		return ResponseEntity.ok().body(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable("id") Long id) throws ObjectNotFoundException {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}

	@PostMapping()	
	public ResponseEntity<Void> insert(@Valid @RequestBody  Usuario usuario) {
		Usuario novoUsuario = service.insert(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping()
	public ResponseEntity<Void> update( @Valid @RequestBody Usuario obj) throws ObjectNotFoundException {
		Usuario usuario = service.update(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable("id") Long id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

}
