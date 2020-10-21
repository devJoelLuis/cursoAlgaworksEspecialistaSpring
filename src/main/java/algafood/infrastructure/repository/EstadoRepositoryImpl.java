package algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import algafood.domain.entidades.Estado;
import algafood.domain.exception.EntidadeNaoEncontradaException;

@Repository
public class EstadoRepositoryImpl  {

	@PersistenceContext
	private EntityManager em;

	// buscar por id
	//@Override
	public Estado buscarPorId(Long id) {
		Estado estado = em.find(Estado.class, id);

		if (estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não foi possível encontrar um estado com o id %d", id));
		}

		return estado;
	}
	
	
	
     // casdastro
	//@Override
	@Transactional
	public Estado adicionar(Estado estado) {
		estado.setId(null);
		return em.merge(estado);
	}

	
	// alterar
	//@Override
	@Transactional
	public Estado alterar(Long id, Estado estado) {
		Estado estadoBanco = em.find(Estado.class, id);

		if (estadoBanco == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(estado, estadoBanco);
		return em.merge(estadoBanco);
	}

	
	
	// get all
	//@Override
	public List<Estado> todos() {
		return em.createQuery("from Estado", Estado.class).getResultList();
	}

	//@Override
	@Transactional
	public void remover(Long id) {
		Estado estadoBanco = buscarPorId(id);

		if (estadoBanco == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não foi possível encontrar um estado com o id %d", id));
		}
		em.remove(estadoBanco);
	}
	
	

}//fecha classe
