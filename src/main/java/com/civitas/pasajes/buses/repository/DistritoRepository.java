package com.civitas.pasajes.buses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civitas.pasajes.buses.entity.Distrito;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    // Buscar País por nombre
	public Distrito findByNombre(String nombre);
}
