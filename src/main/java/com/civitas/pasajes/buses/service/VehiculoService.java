package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.Vehiculo;

public interface VehiculoService {
	public List<Vehiculo> findAll();
    // Buscar País por ID
	public Vehiculo findById(int id);

    // Guardar un nuevo País
    public Vehiculo save(Vehiculo pais);

    // Eliminar un País por ID
    public void delete(int id);
}
