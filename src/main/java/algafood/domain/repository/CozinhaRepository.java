package algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import algafood.domain.entidades.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	List<Cozinha> findByNomeContaining(String nome);

}// fecha classe
