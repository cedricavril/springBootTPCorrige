package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.entities.Entreprise;
import com.example.springboot.entities.Salarie;

public interface SalarieRepository extends CrudRepository<Salarie, Long> {
	List<Salarie> findByNom(String nom);
	
	Salarie findByEntreprise(Entreprise e);
	List<Salarie> findByEntrepriseId(Long id);

}