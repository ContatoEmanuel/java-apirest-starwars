package com.planetas.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planetas.apirest.models.Planeta;

public interface PlanetaRepository extends JpaRepository<Planeta, Long>{

	List<Planeta> findAll();
	Planeta findById(long id);
	Planeta findByNome(String nome);
}
