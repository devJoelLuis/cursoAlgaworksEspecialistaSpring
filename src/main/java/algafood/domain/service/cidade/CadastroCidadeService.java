package algafood.domain.service.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import algafood.domain.entidades.Cidade;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.exception.EstadoNaoEncontradaException;
import algafood.domain.repository.CidadeRepository;
import algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	

	private static final String MSG_CIDADE_EM_USO = "A cidade com o id %d não pode ser excluída porque está em uso!";

	private static final String MSG_CIDADE_NAO_ENCONTRADA_ID = "Não foi possível encontrar uma cidade com o id %d";

	private static final String MSG_ESTADO_NAO_ENCONTRADO_ID = "Não foi possível encontrar um estado com o id %d";

	@Autowired
	private CidadeRepository repo;
	
	@Autowired
	private EstadoRepository repoEs;
	
	
	// cadastrar
	public Cidade adicionar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		repoEs.findById(estadoId)
		      .orElseThrow(() -> new EstadoNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO_ID, estadoId)));
		cidade = repo.save(cidade);
		return cidade;
	}
	
	
	
	// alterar
	public Cidade alterar (Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		repoEs.findById(estadoId)
	      .orElseThrow(() -> new EstadoNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO_ID, estadoId)));   
		
				      
		return repo.save(cidade);
		
		
	}
	
	
	// excluir
	public void remover(Long id) {
		Cidade cidadeAtual = repo.findById(id)
		         .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA_ID, id)));
		try {
			repo.delete(cidadeAtual);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, id));
		}
	}



	public Cidade buscar(Long id) {
		return repo.findById(id)
				   .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA_ID, id)));
	}
	
	

}// fecha classe
