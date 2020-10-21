package algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import algafood.domain.entidades.Cidade;
import algafood.domain.exception.EstadoNaoEncontradaException;
import algafood.domain.exception.NegocioException;
import algafood.domain.repository.CidadeRepository;
import algafood.domain.service.cidade.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository repo;
	
	@Autowired
	private CadastroCidadeService service;
	
	
	
	
	//cadastrar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade){
	 try {
		cidade = service.adicionar(cidade);
		return ResponseEntity.ok(cidade);
	} catch (EstadoNaoEncontradaException e) {
		throw new NegocioException(e.getMessage());
	}
		
	
	}
	
	//alterar
	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> alterar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
	
		Cidade cidadebd = service.buscar(cidadeId);
		BeanUtils.copyProperties(cidade, cidadebd, "id");
		
		try {
			cidade = service.alterar(cidadebd);
			return ResponseEntity.ok(cidade);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
			
		
	}
	
	//excluir
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
	   service.remover(id);	
	}
	
	//get id
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarPorId(@PathVariable Long id) {
			return ResponseEntity.ok(service.buscar(id));
	}
	
	// get all
	@GetMapping
	public List<Cidade> todas() {
		return repo.findAll();
	}
	
}// 
