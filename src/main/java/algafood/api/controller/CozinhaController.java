package algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import algafood.api.resourceModel.CozinhasXmlWrapper;
import algafood.domain.entidades.Cozinha;
import algafood.domain.repository.CozinhaRepository;
import algafood.domain.service.cozinha.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository repo;
	
	@Autowired
	private CadastroCozinhaService service;
	
	
	
	
	// get all response json
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cozinha>> todas() {
		List<Cozinha> cozinhas = repo.findAll();
		return ResponseEntity.ok(cozinhas);
	}
	
	
	
	
	// get all response xml
	@GetMapping
	public CozinhasXmlWrapper listarXml() {
		return new CozinhasXmlWrapper(repo.findAll());
	}
	
	
	
	//cadastro de nova cozinha
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
		Cozinha cozinhaSalva = service.cadastro(cozinha);
		return ResponseEntity.ok(cozinhaSalva);
			
	}
	
	
	//alteração de cozinha
	@PutMapping(value="/{id}")
	public ResponseEntity<Cozinha> alterar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		
			Cozinha cozinhaSalva = service.alterar(id, cozinha);
			return ResponseEntity.ok(cozinhaSalva);
		
		
	}
	
	
	//delete de cozinha
	
	/*	@DeleteMapping(value="/{id}")
		public ResponseEntity<?> excluir(@PathVariable Long id) {	
			try {
				
		     	 service.remove(id);
				 return ResponseEntity.noContent().build();
				 
			//} catch (EntidadeNaoEncontradaException e1)	{
				//return ResponseEntity.notFound().build();
			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
			
		}
		*/
	
		
		@DeleteMapping(value="/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void excluir(@PathVariable Long id) {	
		    	 service.remove(id);	
		}
		
	
	
	// busca por id
	@GetMapping("/{id}")
	public Cozinha buscarPorId(@PathVariable Long id) {
	  return service.buscarCozinha(id);
	}
	
	
	
	
	@GetMapping("/nome")
	public List<Cozinha> buscarPorNome(@RequestParam(value = "nome") String nome) {
		return repo.findByNomeContaining(nome);
	}
	
	
	

}// fecha classe
