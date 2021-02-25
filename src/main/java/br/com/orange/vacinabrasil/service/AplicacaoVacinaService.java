package br.com.orange.vacinabrasil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.orange.vacinabrasil.entities.AplicacaoVacina;
import br.com.orange.vacinabrasil.entities.Usuario;
import br.com.orange.vacinabrasil.exeptions.DataIntegrityException;
import br.com.orange.vacinabrasil.exeptions.ObjectNotFoundException;
import br.com.orange.vacinabrasil.exeptions.ValidacaoAplicacaoVacina;
import br.com.orange.vacinabrasil.repository.AplicacaoVacinaRepository;

@Service
public class AplicacaoVacinaService {

	@Autowired
	AplicacaoVacinaRepository repository;

	@Autowired
	UsuarioService usuarioService;

	public List<AplicacaoVacina> findAll() {
		return repository.findAll();
	}

	public AplicacaoVacina findById(Long id) {
		Optional<AplicacaoVacina> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Essa vacinação não Existe!"));

	}

	public List<AplicacaoVacina> findByUsuarioId(Long id) {
		Optional<List<AplicacaoVacina>> obj = Optional.ofNullable(repository.findByUsuarioId(id));
		return obj.orElseThrow(() -> new ObjectNotFoundException("Esse Usuario não Existe!"));
	}

	public AplicacaoVacina insert(AplicacaoVacina aplicacao) throws ValidacaoAplicacaoVacina {
		List<AplicacaoVacina> lista = findByUsuarioId(aplicacao.getUsuario().getId());
		if (lista.size() < 2) {
			Usuario usuario = usuarioService.findById(aplicacao.getUsuario().getId());
			aplicacao.setUsuario(usuario);
			return repository.save(aplicacao);
		} else {
			throw new ValidacaoAplicacaoVacina("Esse Usuario ja recebeu duas Doses!");
		}

	}

	public AplicacaoVacina update(AplicacaoVacina obj) {

		try {
			Usuario usuario = usuarioService.findById(obj.getUsuario().getId());
			obj.setUsuario(usuario);
			AplicacaoVacina newObj = findById(obj.getId());
			updateData(newObj, obj);
			return repository.save(newObj);
		} catch (ObjectNotFoundException e) {
			throw new DataIntegrityViolationException("Esse Usuário ou a aplicação da vacina não Existe!");
		}

	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir esta vacinação");
		}

	}

	private void updateData(AplicacaoVacina newObj, AplicacaoVacina obj) {
		newObj.setNomeDaVacina(obj.getNomeDaVacina());
		newObj.setUsuario(obj.getUsuario());
		newObj.setDataDaAplicacao(obj.getDataDaAplicacao());
	}

}
