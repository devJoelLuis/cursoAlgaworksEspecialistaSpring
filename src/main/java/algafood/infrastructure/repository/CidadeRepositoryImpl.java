package algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import algafood.domain.entidades.Cidade;
import algafood.domain.entidades.Estado;
import algafood.domain.exception.EntidadeNaoEncontradaException;

@Repository
public class CidadeRepositoryImpl  {
	
	@PersistenceContext
	private EntityManager em;
	

	//@Override
	public Cidade buscarPorId(Long id) {
		Cidade cidade = em.find(Cidade.class, id);
		if (cidade == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não foi possível encontrar uma cidade com o id %d", id));
		}
		return cidade;
	}
	
	
    // cadastrar
	//@Override
	@Transactional
	public Cidade adicionar(Cidade cidade) {
		cidade.setId(null);
		return em.merge(cidade);
	}

	
	//alterar 
	//@Override
	@Transactional
	public Cidade alterar(Long id, Cidade cidade) {
		Cidade cidadeBanco = em.find(Cidade.class, id);
		if (cidadeBanco == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não foi possível encontrar uma cidade com o id %d", id));
		}
		Long estadoId = cidade.getEstado().getId();
		Estado estado = em.find(Estado.class, estadoId);
		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(cidade, cidadeBanco, "id");
		return em.merge(cidadeBanco);
	}

	
	// litar todas
	//@Override
	public List<Cidade> todas() {
		return em.createQuery("from Cidade", Cidade.class).getResultList();
	}

	
	
	// excluir
	//@Override
	@Transactional
	public void remover(Long id) {
		Cidade cidade = buscarPorId(id);
		if (cidade == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não foi possível encontrar uma cidade com o id %d", id));
		}
       em.remove(cidade);
	}

}
