package com.civitas.pasajes.buses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civitas.pasajes.buses.entity.Destinos;

public interface DestinoRepository  extends JpaRepository<Destinos, Integer> {
	
}
