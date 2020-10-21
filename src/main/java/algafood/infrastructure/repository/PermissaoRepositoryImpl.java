package algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import algafood.domain.entidades.Permissao;

@Repository
public class PermissaoRepositoryImpl  {
	
	
	@PersistenceContext
	private EntityManager em;
	

	//@Override
	public Permissao buscarPorId(Long id) {
		return em.find(Permissao.class, id);
	}

	//@Override
	@Transactional
	public Permissao adicionar(Permissao permissao) {
		permissao.setId(null);
		return em.merge(permissao);
	}

	//@Override
	@Transactional
	public Permissao alterar(Permissao permissao) {
		return em.merge(permissao);
	}

	//@Override
	public List<Permissao> todas() {
		return em.createQuery("from Permissao", Permissao.class).getResultList();
	}

	//@Override
	@Transactional
	public void remover(Long id) {
	 Permissao permissao = buscarPorId(id);
	 em.remove(permissao);
	}

}
