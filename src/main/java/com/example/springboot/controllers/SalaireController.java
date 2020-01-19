package com.example.springboot.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.Salaire;
import com.example.springboot.repositories.SalaireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SalaireController {

	@Autowired
	private SalaireRepository salaireRepository;

	@RequestMapping("/salaires")
	public Iterable<Salaire> listSalaires() {
		return salaireRepository.findAll();
	}

	@GetMapping("/salaire/{id}")
	public Salaire getSalaire(@PathVariable Long id) {
		try {
			return salaireRepository.findById(id).orElseThrow();
		} catch(Exception ex) {
			// TODO: 404
			return null;
		}
	}

	@DeleteMapping("/salaire/{id}")
	public void deleteSalaire(@PathVariable Long id) {
		salaireRepository.deleteById(id);
	}

	@PostMapping("/salaire")
	public Salaire saveSalaire(@ModelAttribute("salaire") Salaire s, Model model) {
		salaireRepository.save(s);
		return s;
	}

	@PutMapping("/salaire/{id}")
	public Salaire editSalaire(@PathVariable Long id, @ModelAttribute Salaire s, Model model) {
		salaireRepository.deleteById(id);
		s.setId(id);
		salaireRepository.save(s);
		return s;
	}
}