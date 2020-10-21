package algafood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import algafood.domain.entidades.Restaurante;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.exception.NegocioException;
import algafood.domain.repository.RestauranteRepository;
import algafood.domain.service.restaurante.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository repo;

	@Autowired
	private CadastroRestauranteService service;

	// get all
	@GetMapping
	public ResponseEntity<List<Restaurante>> todos() {
		return ResponseEntity.ok(repo.findAll());
	}

	// get by id
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscar(id));
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
			try {
				restaurante = service.adicionar(restaurante);
				return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
			} catch (EntidadeNaoEncontradaException e) {
				throw new NegocioException(e.getMessage());
			}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		
    		try {
    			restaurante = service.alterar(id, restaurante);
    			return ResponseEntity.ok(restaurante);
			} catch (EntidadeNaoEncontradaException e) {
				throw new NegocioException(e.getMessage());
			}
	}

	// remover
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
	

	// get all por taxa frete
	@GetMapping("/taxafrete")
	public List<Restaurante> porTaxaFrete(@RequestParam(value = "taxa-inicio") BigDecimal taxaInicial,
			@RequestParam(value = "taxa-final") BigDecimal taxaFinal) {
		return repo.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	
	// get all por taxa frete
	@GetMapping("/nome-taxa-frete")
	public List<Restaurante> porNomeTaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return repo.buscarNomeTaxaFreteCriteria(nome, taxaInicial, taxaFinal);
	}
	

	// get all por taxa frete
	@GetMapping("/nome-cozinhaid")
	public List<Restaurante> porNomeCozinhaId(@RequestParam(value = "nome") String nome,
			@RequestParam(value = "cozinhaid") Long cozinhaid) {
		return repo.consultarPorNomeCozinhaId(nome, cozinhaid);
	}
	

	// get all por taxa frete
	@GetMapping("/frete-gratis")
	public List<Restaurante> porFreteGratis(@RequestParam(value = "nome") String nome) {
		
		return repo.buscarComFreteGratis(nome);
	}
	
	
	// get all por taxa frete
		@GetMapping("/primeiro")
		public Optional<Restaurante> buscarPrimeiro() {
			return repo.buscarPrimeiro();
		}
	
	

	// atualizar com path apenas uma propriedade
	@PatchMapping("/{id}")
	public ResponseEntity<?> alteracaoParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

		Optional<Restaurante> rOp = repo.findById(id);

		if (rOp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		merge(campos, rOp.get());

		return alterar(id, rOp.get());

	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {

		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

}// fecha classe
