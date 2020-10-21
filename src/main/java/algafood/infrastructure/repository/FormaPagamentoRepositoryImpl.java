package algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import algafood.domain.entidades.FormaPagamento;

@Repository
public class FormaPagamentoRepositoryImpl  {
	
	@PersistenceContext
	private EntityManager em;
	
	//@Override
	public FormaPagamento buscarPorId(Long id) {
		return em.find(FormaPagamento.class, id);
	}
	
	

	//@Override
	@Transactional
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		formaPagamento.setId(null);
		return em.merge(formaPagamento);
	}

	//@Override
	@Transactional
	public FormaPagamento alterar(FormaPagamento formaPagamento) {
		return em.merge(formaPagamento);
	}

	//@Override
	public List<FormaPagamento> todas() {
		return em.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}

	//@Override
	@Transactional
	public void remover(Long id) {
		FormaPagamento fp = buscarPorId(id);
       em.remove(fp);
	}

}
