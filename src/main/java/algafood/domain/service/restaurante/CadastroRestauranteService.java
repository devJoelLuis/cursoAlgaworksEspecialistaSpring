package algafood.domain.service.restaurante;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import algafood.domain.entidades.Restaurante;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.repository.CozinhaRepository;
import algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO = "O restaurante com id %d não pode ser excluído porque está em uso!";

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO_ID = "Não foi possível encontrar um restaurante com o id %d";

	private static final String MSG_COZINHA_NAO_ENCONTRADA_ID = "Não foi possível encontrar uma cozinha com o id %d";

	@Autowired
	private RestauranteRepository repo;
	
	@Autowired
	private CozinhaRepository repoCo;
	
	
	// adicionar
	public Restaurante adicionar(Restaurante restaurante) {
		
	   Long idcozinha = restaurante.getCozinha().getId();
	   repoCo.findById(idcozinha)
	         .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA_ID, idcozinha)));
	   return repo.save(restaurante);
	  
	}
	
	
	// alterar
	public Restaurante alterar(Long id, Restaurante restaurante) {
		
		Restaurante restauranteAtual = repo.findById(id)
				     .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO_ID, id)));
		 Long idcozinha = restaurante.getCozinha().getId();
		 repoCo.findById(idcozinha)
               .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA_ID, idcozinha)));      
	     BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro");
		 return repo.save(restauranteAtual);
		 
	}


	public Restaurante buscar(Long id) {
		
		return repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format(MSG_RESTAURANTE_NAO_ENCONTRADO_ID, id)));
		
	}


	public void remover(Long id) {
		Restaurante restauranteAtual = repo.findById(id)
			     .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO_ID, id)));
		try {
			repo.delete(restauranteAtual);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, id));
		}			                      
	}
	
	

}//fecha classe
