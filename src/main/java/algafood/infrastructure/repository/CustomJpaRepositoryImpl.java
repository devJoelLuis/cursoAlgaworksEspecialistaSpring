package algafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import algafood.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {
	
	
	private EntityManager em;
	

	

	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}
	
	
	
	@Override
	public Optional<T> buscarPrimeiro() {
		var jpql = "from "+ getDomainClass().getName();// pega o nome da classe que que esta fazendo a consulta
		
		T entity = em.createQuery(jpql, getDomainClass())
		  .setMaxResults(1)
		  .getSingleResult();
		
		return Optional.ofNullable(entity);
	}
	
	

}//fecha classe
