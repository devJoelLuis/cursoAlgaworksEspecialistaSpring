package algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import algafood.domain.entidades.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
}
