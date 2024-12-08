package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.Persona;

public interface PersonaService {
	public Persona findByNombre(String nombre);
	
	public Persona finByDocumentoIdentidad(String dni);
	
	public List<Persona> findAll();
	
	   // Eliminar un País por ID
    public void delete(int id);
    // Buscar País por ID
	public Persona findById(int id);
	 public Persona save(Persona pais);
}
