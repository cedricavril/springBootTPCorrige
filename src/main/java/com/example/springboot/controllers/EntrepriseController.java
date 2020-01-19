package com.example.springboot.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.Entreprise;
import com.example.springboot.entities.Salaire;
import com.example.springboot.entities.Salarie;
import com.example.springboot.repositories.EntrepriseRepository;
import com.example.springboot.repositories.SalarieRepository;

import java.util.List;

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
public class EntrepriseController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private SalarieRepository salarieRepository;

	@RequestMapping("/entreprises")
	public Iterable<Entreprise> listEntreprise() {
		return entrepriseRepository.findAll();
	}

	@GetMapping("/entreprise/{id}")
	public Entreprise getEntreprise(@PathVariable Long id) {
		try {
			return entrepriseRepository.findById(id).orElseThrow();
		} catch(Exception ex) {
			// TODO: 404
			return null;
		}
	}

	@PostMapping("/entreprise_salarie/{id}")
	public Salarie addSalarie(@ModelAttribute("Salarie") Salarie salarie, Model model, @PathVariable Long id) {
		salarieRepository.save(salarie);
//		entrepriseRepository.addSalarie(salarie);
		return salarie;
	}

	@PostMapping("/entreprise/testavecnomdentreprise")
	public List<Entreprise> getEntreprise(@ModelAttribute("Entreprise") Entreprise e, Model model) {
		try {
			return entrepriseRepository.findByRaisonSociale(e.getRaisonSociale());
		} catch(Exception ex) {
			// TODO: 404
			return null;
		}
	}

	@DeleteMapping("/entreprise/{id}")
	public void deleteEntreprise(@PathVariable Long id) {
		List<Salarie> ls = salarieRepository.findByEntrepriseId(id);
		salarieRepository.deleteAll(ls);
		entrepriseRepository.deleteById(id);
	}

	@PostMapping("/entreprise")
	public Entreprise createEntreprise(@ModelAttribute Entreprise e, @ModelAttribute Salarie s, @ModelAttribute Salaire salaire) {
		entrepriseRepository.save(e);
		return e;
	}

	@PutMapping("/entreprise/{id}")
	public Entreprise updateEntreprise(@PathVariable Long id, @ModelAttribute Entreprise e, Model model) {
		e.setId(id);
		entrepriseRepository.save(e);
		return e;
	}
}