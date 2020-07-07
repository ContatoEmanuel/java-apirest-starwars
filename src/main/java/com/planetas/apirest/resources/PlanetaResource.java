package com.planetas.apirest.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		public ResponseEntity<Planeta> adicionaPlaneta(@RequestBody @Valid Planeta planeta){
			return new ResponseEntity<Planeta>(planetaRepository.save(planeta),HttpStatus.CREATED);
		}
		
		@GetMapping("/planetas")
		@ApiOperation(value="Retorna a Lista de Planetas.")
		public ResponseEntity<List<Planeta>> listaPlanetas(){
		List<Planeta> listaPlanetas = planetaRepository.findAll();
			if(listaPlanetas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<List<Planeta>>(listaPlanetas,HttpStatus.OK);
			}
				
		}
		
		@GetMapping("/planeta/{nome}")
		@ApiOperation(value="Acessa o Planeta pelo Nome.")
		public ResponseEntity<Planeta> buscaPlanetaNome(@PathVariable(value="nome")String nome){
			Optional<Planeta> planetaO = Optional.ofNullable(planetaRepository.findByNome(nome));
			if(!planetaO.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Planeta>(planetaO.get(),HttpStatus.OK);
			}
		}		
		@GetMapping("/planeta/id/{id}")
		@ApiOperation(value="Acessa o Planeta pelo Id.")
		public ResponseEntity<Planeta> buscaPlanetaId(@PathVariable(value="id")long id){
			Optional<Planeta> planetaO = Optional.ofNullable(planetaRepository.findById(id));
			if(!planetaO.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Planeta>(planetaO.get(),HttpStatus.OK);
			}
		}
		
		@DeleteMapping("/planeta/id/{id}")
		@ApiOperation(value="Deleta um Planeta pelo Id.")
		public ResponseEntity<?> removePlanetaId(@PathVariable(value="id")long id){
			Optional<Planeta> planetaO = Optional.ofNullable(planetaRepository.findById(id));
			if(!planetaO.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				planetaRepository.delete(planetaO.get());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		@PutMapping("/planeta/id/{id}")
		@ApiOperation(value="Altera as Informações de um Planeta pelo Id.")
		public ResponseEntity<Planeta> atualizaPlanetaId(@PathVariable(value="id") long id, @RequestBody 
				@Valid Planeta planeta){
			Optional<Planeta>planetaO = Optional.ofNullable(planetaRepository.findById(id));
			if(!planetaO.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				planeta.setId(planetaO.get().getId());
				return new ResponseEntity<Planeta>(planetaRepository.save(planeta), HttpStatus.OK);
			}
		}
}