package com.example.springboot.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.Entreprise;
import com.example.springboot.entities.Salarie;
import com.example.springboot.repositories.EntrepriseRepository;
import com.example.springboot.repositories.SalarieRepository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SalarieController {

	@Autowired
	private SalarieRepository salarieRepository;

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@RequestMapping("/salaries")
	public Iterable<Salarie> listSalaries() {
		return salarieRepository.findAll();
	}

	@GetMapping("/salarie/{id}")
	public Salarie getSalarie(@PathVariable Long id) {
		try {
			return salarieRepository.findById(id).orElseThrow();
		} catch(Exception ex) {
			// TODO: 404
			return null;
		}
	}

	@GetMapping("/salarie/{nom}")
	public List<Salarie> getSalarie(@PathVariable String nom) {
		return salarieRepository.findByNom(nom);
	}
	
	@DeleteMapping("/salarie/{id}")
	public void deleteSalarie(@PathVariable Long id) {
		salarieRepository.deleteById(id);
	}

	@PostMapping("/salarie/{id}")
	public Salarie saveSalarie(@Valid @ModelAttribute("salarie") Salarie s, BindingResult result, Model model, @PathVariable Long id) {
		Entreprise entreprise = entrepriseRepository.findById(id).get();

		new SalarieValidator().validate(s, result);		

		if (result.hasErrors()) {
			System.err.println("L'utilisateur n'a pas été validé ...");
			return null;
		}
		System.out.println("Prénom : " + s.getPrenom() + " Nom : " + s.getNom());

		s.setEntreprise(entreprise);
		salarieRepository.save(s);
		return s;
	}

	@PutMapping("/salarie/{id}")
	public Salarie editSalarie(@PathVariable Long id, @ModelAttribute Salarie s, Model model) {
		salarieRepository.deleteById(id);
		s.setIdSalarie(id);
		salarieRepository.save(s);
		return s;
	}
	
	
	public class SalarieValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return Salarie.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "prenom.empty", "Le prénom doit être saisi");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "nom.empty", "Le nom doit être saisi");
		}
	}
}