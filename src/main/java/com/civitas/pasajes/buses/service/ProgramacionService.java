package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.Programacion;

public interface ProgramacionService {
	public List<Programacion> findAll();


    // Guardar un nuevo País
    public Programacion save(Programacion ciudad);

    // Eliminar un País por ID
    public void delete(int id);
}
