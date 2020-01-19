package com.example.springboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.entities.Salaire;

public interface SalaireRepository extends CrudRepository<Salaire, Long> {

}