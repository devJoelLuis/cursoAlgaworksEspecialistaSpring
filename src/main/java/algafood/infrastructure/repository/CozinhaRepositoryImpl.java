package algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import algafood.domain.entidades.Cozinha;


@Repository
public class CozinhaRepositoryImpl  {

	@PersistenceContext
	private EntityManager em;
	
	//@Override
	public List<Cozinha> todas() {
		// cria uma consulta
		TypedQuery<Cozinha> query = em.createQuery("from Cozinha", Cozinha.class);
		
		//o query retornará uma lista de cozinhas porque foi typado na linha anterior
		return query.getResultList();
	}
	
	//@Override
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return em.merge(cozinha);
	}
	
	//@Override
	@Transactional
	public Cozinha buscarPorId(Long id) {
		return em.find(Cozinha.class, id);
	}
	
	//@Override
	@Transactional
	public void remover(Long id) {
		Cozinha cozinha = buscarPorId(id);
		
		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		em.remove(cozinha);
	}

	
	//alterar
	//@Override
	@Transactional
	public Cozinha alterar(Long id, Cozinha cozinha) {
		
         Cozinha cozinhaBanco = buscarPorId(id);
		
		if (cozinhaBanco == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(cozinha, cozinhaBanco);
		return em.merge(cozinhaBanco);
	}

	
	
	
	
	//busca por nome
	//@Override
	public List<Cozinha> buscarPorNome(String nome) {
		return em.createQuery("from Cozinha where nome like :nome", Cozinha.class)
				.setParameter("nome", "%"+nome+"%")
				.getResultList();
	}
	
	
	
}//fecha classe
