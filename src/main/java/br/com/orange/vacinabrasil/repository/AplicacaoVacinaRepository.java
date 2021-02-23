package br.com.orange.vacinabrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.orange.vacinabrasil.entities.AplicacaoVacina;

@Repository
public interface AplicacaoVacinaRepository  extends JpaRepository<AplicacaoVacina, Long>{
	
	@Transactional(readOnly = true)
	AplicacaoVacina findByUsuarioId(Long id);

}
