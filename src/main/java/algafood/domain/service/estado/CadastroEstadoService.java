package algafood.domain.service.estado;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import algafood.domain.entidades.Estado;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.EstadoNaoEncontradaException;
import algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO = "O Estado de id %d não pode ser removido porque está em uso";
	private static final String MSG_ESTADO_NAO_ENCONTRADO_ID = "Não foi possível encontrar um estado com o id %d.";
	
	@Autowired
	private EstadoRepository repo;
	
	
	// cadastrar
	public Estado adicionar(Estado estado) {
		estado = repo.save(estado);
		return estado;
	}
	
	// alterar
	public Estado alterar(Long id, Estado estado) {
		Estado estadoAtual = repo.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO_ID, id)));
		BeanUtils.copyProperties(estado, estadoAtual);
		return repo.save(estadoAtual);
	}
	
	//excluir
	public void remover(Long id) {
		Estado estado = repo.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO_ID, id)));
		try {
			repo.delete(estado);
		} catch (DataIntegrityViolationException e) {
		   throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
		}
	}
	
	
    // buscar por id
	public Estado buscar(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_ENCONTRADO_ID, id)));
	}
	
}
