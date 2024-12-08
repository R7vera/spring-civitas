package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.Departamento;


public interface DepartamentoService {
	
	public Departamento findByNombre(String nombre);
	 
	public List<Departamento> findAll();
    // Buscar País por ID
	public Departamento findById(int id);

    // Guardar un nuevo País
    public Departamento save(Departamento departamento);

    // Eliminar un País por ID
    public void delete(int id);
    
    
}
