package com.example.springboot.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Salarie implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idSalarie;

	private String nom;

	private String prenom;
	
	@Embedded
	private Adresse adresse;
	
	@ManyToOne
	@JoinColumn
	private Entreprise entreprise;

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	@OneToOne
	private Salaire salaire;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Long getIdSalarie() {
		return idSalarie;
	}

	public void setIdSalarie(Long id) {
		this.idSalarie = id;
	}
}
