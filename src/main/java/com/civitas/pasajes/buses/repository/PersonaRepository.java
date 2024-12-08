package com.civitas.pasajes.buses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civitas.pasajes.buses.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	public Persona findByNombres(String nombres);
	public Persona findByDocumentoIdentidad(String dni);
}
