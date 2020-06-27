package com.planetas.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planetas.apirest.models.Planeta;
import com.planetas.apirest.repository.PlanetaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/api")
@Api(value="API REST Planetas")
@CrossOrigin(origins="*")
public class PlanetaResource {

		@Autowired
		PlanetaRepository planetaRepository;
		
		@PostMapping("/planeta")
		@ApiOperation(value="Adiciona um Planeta.")
		public Planeta adicionaPlaneta(@RequestBody Planeta planeta) {
			return planetaRepository.save(planeta);
		}
		
		@GetMapping("/planetas")
		@ApiOperation(value="Retorna a Lista de Planetas.")
		public List<Planeta> listaPlanetas(){
			return planetaRepository.findAll();
		}
		
		@GetMapping("/planeta/{nome}")
		@ApiOperation(value="Acessa o Planeta pelo Nome.")
		public Planeta buscaPlanetaNome(@PathVariable(value="nome")String nome){
			return planetaRepository.findByNome(nome);
		}
		
		@GetMapping("/planeta/id/{id}")
		@ApiOperation(value="Acessa o Planeta pelo Id.")
		public Planeta buscaPlanetaId(@PathVariable(value="id")long id){
			return planetaRepository.findById(id);
		}
		
		@DeleteMapping("/planeta")
		@ApiOperation(value="Deleta um Planeta.")
		public void removePlaneta(@RequestBody Planeta planeta) {
			planetaRepository.delete(planeta);
		}
		
		@PutMapping("/planeta")
		@ApiOperation(value="Altera as Informações de um Planeta.")
		public Planeta atualizaPlaneta(@RequestBody Planeta planeta) {
			return planetaRepository.save(planeta);
		}
}