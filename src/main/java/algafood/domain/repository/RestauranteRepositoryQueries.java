package algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import algafood.domain.entidades.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> buscarNomeTaxaFreteJpql(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> buscarNomeTaxaFreteCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> buscarComFreteGratis(String nome);
	
	//List<Restaurante> buscar

}