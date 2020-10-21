package algafood.api.controller;

import java.util.List;

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

import algafood.domain.entidades.Estado;
import algafood.domain.repository.EstadoRepository;
import algafood.domain.service.estado.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository repo;

	@Autowired
	private CadastroEstadoService service;

	// get all
	@GetMapping
	public List<Estado> todos() {
		return repo.findAll();
	}

	// buscar por id
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
		
			return ResponseEntity.ok(service.buscar(id));
		
	}

	// cadastrar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
		estado = service.adicionar(estado);
		return ResponseEntity.ok(estado);
	}

	
	// cadastrar
	@PutMapping("/{id}")
	public ResponseEntity<?> Alterar(@PathVariable Long id, @RequestBody Estado estado) {
	    return ResponseEntity.ok(service.alterar(id, estado));
	}

	// excluir
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
	   service.remover(id);
	}

}// fecha classe
