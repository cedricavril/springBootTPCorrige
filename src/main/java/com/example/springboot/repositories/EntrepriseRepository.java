package com.example.springboot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.entities.Entreprise;

public interface EntrepriseRepository extends CrudRepository<Entreprise, Long> {
	List<Entreprise> findByRaisonSociale(String raisonSociale);
	
	Optional<Entreprise> findById(Long id);
	
}