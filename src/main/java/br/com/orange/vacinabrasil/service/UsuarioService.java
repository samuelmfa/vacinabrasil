package br.com.orange.vacinabrasil.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.orange.vacinabrasil.entities.Usuario;
import br.com.orange.vacinabrasil.exeptions.DataIntegrityException;
import br.com.orange.vacinabrasil.exeptions.ObjectNotFoundException;
import br.com.orange.vacinabrasil.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> findAll() {
		List<Usuario> usuarios = repository.findAll();
		return usuarios;
	}

	public Usuario findById(Long id){
		Optional<Usuario> usuario = repository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));

	}

	public Usuario findByCpfAndEmail(String cpf, String email) {
		return repository.findByNumeroDoCpfOrEmail(cpf, email);
	}

	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Usuario findByCPF(String cpf) {
		return repository.findByNumeroDoCpf(cpf);

	}

	public Usuario insert(Usuario usuario) {
		Usuario obj = findByCpfAndEmail(usuario.getNumeroDoCpf(), usuario.getEmail());
		if (obj == null) {
			return repository.save(usuario);
		} else {
			throw new RuntimeException(
					"CPF/Email já Cadastrado! Id: " + usuario.getNumeroDoCpf() + ", Tipo: " + Usuario.class.getName());
		}

	}
	
	@Transactional
	public Usuario update(Usuario obj) throws ObjectNotFoundException {
		Usuario newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);

	}

	public void delete(Long id) {
		findById(id);
		try {			
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir esta vacinação");
		}

	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setNumeroDoCpf(obj.getNumeroDoCpf());
		newObj.setDataDeNascimento(obj.getDataDeNascimento());

	}

}
