package com.planetas.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@RequestMapping(value="/api")
public class PlanetaResource {

		@Autowired
		PlanetaRepository planetaRepository;
		
		@PostMapping("/planeta")
		public Planeta adicionaPlaneta(@RequestBody Planeta planeta) {
			return planetaRepository.save(planeta);
		}
		
		@GetMapping("/planetas")
		public List<Planeta> listaPlanetas(){
			return planetaRepository.findAll();
		}
		
		@GetMapping("/planeta/{nome}")
		public Planeta buscaPlanetaNome(@PathVariable(value="nome")String nome){
			return planetaRepository.findByNome(nome);
		}
		
		@GetMapping("/planeta/id/{id}")
		public Planeta buscaPlanetaId(@PathVariable(value="id")long id){
			return planetaRepository.findById(id);
		}
		
		@DeleteMapping("/planeta")
		public void removePlaneta(@RequestBody Planeta planeta) {
			planetaRepository.delete(planeta);
		}
		
		@PutMapping("/planeta")
		public Planeta atualizaPlaneta(@RequestBody Planeta planeta) {
			return planetaRepository.save(planeta);
		}
}