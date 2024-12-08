package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.Distrito;

public interface DistritoService {
	public Distrito findByNombre(String nombre);
	 
	public List<Distrito> findAll();
    // Buscar País por ID
	public Distrito findById(int id);

    // Guardar un nuevo País
    public Distrito save(Distrito distrito);

    // Eliminar un País por ID
    public void delete(int id);
}
