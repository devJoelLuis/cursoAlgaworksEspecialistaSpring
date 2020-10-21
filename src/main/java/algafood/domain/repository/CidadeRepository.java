package algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import algafood.domain.entidades.Cidade;

public interface CidadeRepository  extends JpaRepository<Cidade, Long> {

	boolean existsByEstadoId(Long idestado);
	

}//fecha classe
