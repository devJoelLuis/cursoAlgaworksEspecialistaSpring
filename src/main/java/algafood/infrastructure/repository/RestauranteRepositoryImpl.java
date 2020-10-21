package algafood.infrastructure.repository;

import static algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import algafood.domain.entidades.Restaurante;
import algafood.domain.repository.RestauranteRepository;
import algafood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries  {
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired @Lazy
	private RestauranteRepository repo;
	
	// JPQL
	// consulta dinâmica ou por nome, ou por taxa frete inicial, ou por taxa frete final ou por nenhum (retorna tudo)
    @Override
	public List<Restaurante> buscarNomeTaxaFreteJpql(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
    	// cria uma variável contendo o inicial de uma consulta jpql 
    	var jpql = new StringBuilder();
    	jpql.append("from Restaurante where 0 = 0 "); // where 0 = 0 sempre será verdadeiro
    	
    	// cria um hash para armazenar o parametro e valor
    	var parametros = new HashMap<String, Object>();
    	
    	// se tiver nome para pesquisar
    	if (StringUtils.hasLength(nome)) {
    		jpql.append("and nome like : nome "); //adiciona a consulta por nome no jpql
    		parametros.put("nome", "%"+ nome + "%"); // adiciona o parametro nome no hash
    	}
    	
    	// se tiver taxa inicial para pesquisar
    	if (taxaFreteFinal != null) {
    		jpql.append("and taxaFrete >= :taxaInicial ");//adiciona a consulta por taxa frete inicial no jpql
    		parametros.put("taxaInicial", taxaFreteInicial); // adiciona o parametro taxaInicial no hash
    	}
    	
    	// se tiver taxa final para pesquisar
    	if (taxaFreteFinal != null) {
    		jpql.append("and taxaFrete <= :taxaFinal");//adiciona a consulta por taxa frete final no jpql
    		parametros.put("taxaFinal", taxaFreteFinal); // adicional o parametro no hash
    	}
    	
    	//cria uma query jpql do tipo Restaurante
    	TypedQuery<Restaurante> query =  em.createQuery(jpql.toString(), Restaurante.class);
    	
    	// se tiver parametros no hash adiciona na query criada
    	parametros.forEach((chave, valor) -> {
    		query.setParameter(chave, valor);
    	});
    			     
    	return query.getResultList();		    
    }
    
    
    
    // CRITERIA JPQL
 	// consulta dinâmica ou por nome, ou por taxa frete inicial, ou por taxa frete final ou por nenhum (retorna tudo)
     @Override
 	public List<Restaurante> buscarNomeTaxaFreteCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
    	 
    	 // cria um fabrica (build) de criteria
    	 CriteriaBuilder b = em.getCriteriaBuilder(); 
    	 
    	//criando uma query da criteria
    	 CriteriaQuery<Restaurante> criteria = b.createQuery(Restaurante.class);
    	 
    	 //cria os metodos de consulta
    	 Root<Restaurante> root = criteria.from(Restaurante.class); // retornar um root dos campos de restaurante
    	 
    	 //criar um arry de predicate que é utilizado na clausa where da criteria
    	 var predicates = new ArrayList<Predicate>();
    	 
    	 //criar um Predicate se a variável não for null StringUtils.hasText verifica se a string não e null e não é vazia
    	 if (StringUtils.hasText(nome)) {
    		 predicates.add(b.like(root.get("nome"), "%" + nome + "%"));
    	 }
         if (taxaFreteInicial != null) {
        	 predicates.add(b.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
         }
         if (taxaFreteFinal != null) {
        	 predicates.add(b.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
         }
    	 
    	 
    	 //passa os predicados de consulta para where do criteria
    	 criteria.where(predicates.toArray(new Predicate[0]));// predicates.toArray(new Predicate[0]) converte uma ArrayList em array
    	 
        TypedQuery<Restaurante> query = em.createQuery(criteria);
        
        return query.getResultList();
   
     }//fecha função



	@Override
	public List<Restaurante> buscarComFreteGratis(String nome) {
		return repo.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
	
	

}//fecha classe
