package com.civitas.pasajes.buses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.civitas.pasajes.buses.entity.Pais;



@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    // Buscar País por nombre
	public Pais findByNombre(String nombre);
}
