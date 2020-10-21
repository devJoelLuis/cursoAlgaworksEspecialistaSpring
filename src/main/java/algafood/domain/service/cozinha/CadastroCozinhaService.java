package algafood.domain.service.cozinha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import algafood.domain.entidades.Cozinha;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "A cozinha de id %d não pode ser excluída porque está em uso.";

	private static final String MSG_COZINHA_NAO_ENCOTRADA = "Não foi possível encotrar uma cozinha com o id %d";
	
	@Autowired
	private CozinhaRepository repo;
	

	// cadastro
	public Cozinha cadastro(Cozinha cozinha) {
		return repo.save(cozinha);
	}
	
	

	// alterar
	public Cozinha alterar(Long id, Cozinha cozinha) {
		Cozinha cozinhaAtual = repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_COZINHA_NAO_ENCOTRADA, id)));
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return repo.save(cozinhaAtual);
		
	}
	
	
	
	

	// remove
	public void remove(Long id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCOTRADA, id));
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		}
	}
	
	
	public Cozinha buscarCozinha(Long id) {
		 return repo.findById(id)
			      .orElseThrow(() -> new EntidadeNaoEncontradaException(
			    		  String.format(MSG_COZINHA_NAO_ENCOTRADA, id)));
		
	}
	
	

}// fecha classe
