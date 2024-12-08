package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.Ciudad;

public interface CiudadService {
	public Ciudad findByNombre(String nombre);
	 
	public List<Ciudad> findAll();
    // Buscar País por ID
	public Ciudad findById(int id);

    // Guardar un nuevo País
    public Ciudad save(Ciudad ciudad);

    // Eliminar un País por ID
    public void delete(int id);
}
